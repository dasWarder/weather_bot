package by.itechart.weather_bot.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableCaching
public class AppConfig {

    public static final String WEATHER_CACHE = "weather";
    public static final String DRIVE_CACHE = "drive";
    public static final String FORECAST_CACHE = "forecast";

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(WEATHER_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1)))
                .withCacheConfiguration(FORECAST_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1)))
                .withCacheConfiguration(DRIVE_CACHE,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1)));
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
