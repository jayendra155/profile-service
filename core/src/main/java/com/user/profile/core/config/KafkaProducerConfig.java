package com.user.profile.core.config;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import brave.Tracing;
import brave.kafka.clients.KafkaTracing;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 18/04/22
 */

@Data
@Slf4j
@Configuration
@ConditionalOnProperty(name = "kafka.producer.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaProducerConfig {

    @Value("${kafka.broker.urls}")
    private String brokerUrls;

    @Value("${spring.application.name}")
    private String appName;

    private Map<String, Object> createKafkaProperties() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(BOOTSTRAP_SERVERS_CONFIG, this.brokerUrls);
        producerProps.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return producerProps;
    }

    @Bean
    public SenderOptions<String, String> senderOptions() {
        return SenderOptions.<String, String>create(createKafkaProperties());
    }

    @Bean("kafkaProducer")
    KafkaSender<String, String> kafkaProducer(KafkaTracing kafkaTracing,
            SenderOptions<String, String> senderOptions) {
        TraceableProducerFactory obj = TraceableProducerFactory.INSTANCE;
        obj.setKafkaTracing(kafkaTracing);
        return KafkaSender.create(obj, senderOptions);

    }

    @Bean
    KafkaTracing kafkaTracing(Tracing tracing) {
        return KafkaTracing.create(tracing);
    }

    @Override
    public String toString() {
        return "KafkaProducerConfig{" + "brokerUrls='" + brokerUrls + '\'' + ", app name=" + this.getAppName() + '}';
    }

    @PostConstruct
    public void init() {
        log.info("Initializing kafka with props : {}", toString());
    }
}
