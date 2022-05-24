package com.user.profile.core.config;

import java.time.Duration;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.Assert;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 27/04/22
 */
@Configuration
public class CacheConfig {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("businessProfileCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
    }

    @Bean(name = "businessProfileKeyGenerator")
    public KeyGenerator businessProfileKeyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Bean(name = "businessProfileObjectKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            Assert.notNull(params, "Elements must not be null");
            if (params.length > 0) {
                if (params[0] instanceof BusinessProfileUpdateRequestDoc) {
                    return ((BusinessProfileUpdateRequestDoc) params[0]).getBusinessProfileId();
                }
            }
            throw new NullPointerException("Unable to identify key");
        };
    }

}
