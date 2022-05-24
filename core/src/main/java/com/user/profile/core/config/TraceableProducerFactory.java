package com.user.profile.core.config;

import org.apache.kafka.clients.producer.Producer;
import brave.kafka.clients.KafkaTracing;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.internals.ProducerFactory;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */
public class TraceableProducerFactory extends ProducerFactory {

    private KafkaTracing kafkaTracing;

    public static final TraceableProducerFactory INSTANCE = new TraceableProducerFactory();

    protected TraceableProducerFactory() {
        super();
    }

    @Override
    public <K, V> Producer<K, V> createProducer(SenderOptions<K, V> senderOptions) {
        Producer<K, V> producer = super.createProducer(senderOptions);
        return this.kafkaTracing.producer(producer);
    }

    public void setKafkaTracing(KafkaTracing kafkaTracing) {
        this.kafkaTracing = kafkaTracing;
    }
}
