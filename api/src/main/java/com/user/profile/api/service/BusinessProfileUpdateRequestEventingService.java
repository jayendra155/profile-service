package com.user.profile.api.service;

import java.util.concurrent.ExecutorService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.user.profile.api.orchestration.interfaces.Action;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import com.user.profile.model.dto.BusinessProfileUpdateResponseDTO;
import com.user.profile.model.mapper.BusinessProfileMapper;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Service
public class BusinessProfileUpdateRequestEventingService
        implements Action<BusinessProfileUpdateRequestDoc, BusinessProfileUpdateResponseDTO> {

    @Autowired
    @Qualifier("kafkaProducer")
    private KafkaSender<String, String> kafkaProducer;

    @Autowired
    @Qualifier("kafkaExecutor")
    private ExecutorService kafkaExecutor;

    @Value("${kafka.producer.business-profile-update.topic}")
    private String topic;

    @Override
    public BusinessProfileUpdateResponseDTO execute(BusinessProfileUpdateRequestDoc msg) {

        String key = String.valueOf(msg.getBusinessProfileId());
        kafkaProducer.send(Flux.just(createSenderRecord(key, msg.getId().toString(), topic)))
                .publishOn(Schedulers.fromExecutor(kafkaExecutor))
                .doOnNext(c -> {
                    RecordMetadata recordMetadata = c.recordMetadata();
                    log.debug("Key : {} partition : {}, offset : {}, topic: {}", key, recordMetadata.partition(),
                            recordMetadata.offset(), recordMetadata.topic());
                })
                .doOnError(TimeoutException.class, e -> log.error("Timeout error while publishing to kafka"))
                .doOnError(e -> log.error("Error {}", e.getMessage(), e))
                .publishOn(Schedulers.immediate()).subscribe();
        return BusinessProfileMapper.MAPPER_INSTANCE.fromUpdateDocument(msg);
    }

    private SenderRecord<String, String, Object> createSenderRecord(String key, String value, String topic) {
        return SenderRecord.create(topic, null, System.currentTimeMillis(), key, value, null);
    }

}
