package com.user.profile.worker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 02/05/22
 */
@Slf4j
@Configuration
public class WebConfig {

    @Bean
    public WebClient webClient(ObjectMapper objectMapper) {
        ExchangeStrategies jacksonStrategy = ExchangeStrategies.builder().codecs(config -> {
            config.defaultCodecs()
                    .jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
            config.defaultCodecs()
                    .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
        }).build();
        return WebClient.builder().exchangeStrategies(jacksonStrategy).build();
    }
}
