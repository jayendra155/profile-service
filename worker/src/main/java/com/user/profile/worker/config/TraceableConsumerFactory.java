package com.user.profile.worker.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import brave.Tracing;
import brave.kafka.clients.KafkaTracing;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.internals.ConsumerFactory;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
public class TraceableConsumerFactory extends ConsumerFactory {

    public static final TraceableConsumerFactory INSTANCE = new TraceableConsumerFactory();

    @Setter
    private Tracing tracing;

    @Setter
    private KafkaTracing kafkaTracing;

    @Getter
    private Consumer consumer;

    protected TraceableConsumerFactory() {
        super();
    }

    @Override
    public <K, V> Consumer<K, V> createConsumer(ReceiverOptions<K, V> config) {
        log.info("TraceableConsumerFactory invoked");
        KafkaConsumer<K, V> kc = (KafkaConsumer<K, V>) super.createConsumer(config);
        Consumer<K, V> kvConsumer = this.kafkaTracing.consumer(kc);
        this.consumer = kvConsumer;
        return kvConsumer;
    }

}
