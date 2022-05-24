package com.user.profile.worker.kafka.config;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.user.profile.worker.config.TraceableConsumerFactory;
import brave.Tracing;
import brave.kafka.clients.KafkaTracing;
import lombok.extern.slf4j.Slf4j;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
@Configuration
public class BusinessProfileUpdateRequestConsumerConfig {

    @Value("${kafka.consumer.business-profile-update.topic}")
    private String topic;

    @Value("${kafka.broker.urls:localhost:9092}")
    private String kafkaBrokerUrls;

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean(name = "profileUpdateRequestConsumer")
    public ReceiverOptions<String, String> profileUpdateRequestConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerUrls);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, this.applicationName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.<String, String>create(props);
        receiverOptions.commitInterval(Duration.ZERO)
                       .commitBatchSize(0);
        log.info("Topic to read from : {}", topic);
        ReceiverOptions<String, String> options = receiverOptions.subscription(
                        Collections.singleton(topic))
                .addAssignListener(partitions -> log.info("PartitionsAssigned : {}", partitions))
                .addRevokeListener(partitions -> log.info("onPartitionsRevoked {}", partitions));
        return options;
    }

    @Bean(name = "profileUpdateRequestReceiver")
    public KafkaReceiver<String, String> profileUpdateRequestReceiver(
            ReceiverOptions<String, String> profileUpdateRequestConsumer, KafkaTracing kafkaTracing, Tracing tracing) {
        TraceableConsumerFactory factory = TraceableConsumerFactory.INSTANCE;
        factory.setTracing(tracing);
        factory.setKafkaTracing(kafkaTracing);
        return KafkaReceiver.create(factory, profileUpdateRequestConsumer);
    }


}
