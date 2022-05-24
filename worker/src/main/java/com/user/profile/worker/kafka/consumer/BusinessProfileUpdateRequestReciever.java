package com.user.profile.worker.kafka.consumer;

import static com.user.profile.worker.kafka.utils.KafkaUtils.traceRecord;
import java.util.Optional;
import java.util.concurrent.Executor;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.profile.worker.AppConstants;
import com.user.profile.worker.kafka.model.TraceableReceiverRecord;
import brave.Tracing;
import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.util.Loggers;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Component
public class BusinessProfileUpdateRequestReciever {

    @Autowired
    @Qualifier("profileUpdateRequestReceiver")
    private KafkaReceiver<String, String> profileUpdateRequestReceiver;

    @Autowired
    @Qualifier("profileUpdateRequestExecutor")
    private Executor profileUpdateRequestExecutor;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Tracing tracing;

    @Autowired
    private CamelContext context;

    @Produce(value = AppConstants.UPDATE_ROUTE + "?lazyStartProducer=true")
    private ProducerTemplate profileUpdate;

    private boolean consumptionStarted;

    @EventListener(ApplicationReadyEvent.class)
    public void eventConsumption() {
        log.info("App ready event triggered");
        if (context.isStarting()) {
            return;
        }
        if (!consumptionStarted) {
            log.info("App is ready. Starting profile update request consumer");
            //@formatter:off
            Optional.ofNullable(this.profileUpdateRequestReceiver)

                    .ifPresent(receiver -> receiver.receive()
                        .map(TraceableReceiverRecord::new)
                        .doOnNext(rec -> traceRecord(rec, tracing))
                        .log(Loggers.getLogger(log.getName()))
                        .publishOn(Schedulers.fromExecutor(this.profileUpdateRequestExecutor))
                        .map(this::onMessage)
                        .doOnError(e -> log.error("Error in processing, {}", e.getMessage(), e))
                        .publishOn(Schedulers.immediate())
                        .subscribe());
            //@formatter:on
            consumptionStarted = true;
        }
    }

    protected ReceiverRecord<String, String> onMessage(ReceiverRecord<String, String> receiverRecord) {
        log.info("triggering route for profile update");
        try {
            String id = receiverRecord.value();
            this.profileUpdate.sendBody(id);
            log.info("committing kafka message for profile id: {}", id);
            receiverRecord.receiverOffset().commit().block();
        } catch (CamelExecutionException c) {
            log.error("Something went wrong in camel route. {}", c.getMessage(), c);
        } catch (IllegalStateException ie) {
            log.error("Unable to start consumer: {}, ", ie.getMessage(), ie);
        } catch (NullPointerException npe) {
            log.error("Null pointer found. {}, ", npe.getMessage(), npe);
        }
        log.info("Received: {}", receiverRecord.value());
        return receiverRecord;
    }
}
