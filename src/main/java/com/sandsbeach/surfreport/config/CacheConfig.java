package com.sandsbeach.surfreport.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    public static final String CACHE_NAME_SURFLIES_RATING = "SURFLIES_RATING";
    @Bean
    public CaffeineCache cacheSurfliesRating() {
        return new CaffeineCache(CACHE_NAME_SURFLIES_RATING,
                Caffeine.newBuilder()
                        .expireAfterAccess(56, TimeUnit.MINUTES)
                        .build());
    }

    public static final String CACHE_NAME_SURFLIES_WAVE = "SURFLIES_WAVE";
    @Bean
    public CaffeineCache cacheSurfliesWave() {
        return new CaffeineCache(CACHE_NAME_SURFLIES_WAVE,
                Caffeine.newBuilder()
                        .expireAfterAccess(56, TimeUnit.MINUTES)
                        .build());
    }

    public static final String CACHE_NAME_SURFLIES_WIND = "SURFLIES_WIND";
    @Bean
    public CaffeineCache cacheSurfliesWind() {
        return new CaffeineCache(CACHE_NAME_SURFLIES_WIND,
                Caffeine.newBuilder()
                        .expireAfterAccess(56, TimeUnit.MINUTES)
                        .build());
    }

    public static final String CACHE_NAME_SURFLIES_TIDES = "SURFLIES_TIDES";
    @Bean
    public CaffeineCache cacheSurfliesTides() {
        return new CaffeineCache(CACHE_NAME_SURFLIES_TIDES,
                Caffeine.newBuilder()
                        .expireAfterAccess(56, TimeUnit.MINUTES)
                        .build());
    }

    public static final String CACHE_NAME_SURFLIES_BUOY = "SURFLIES_BUOY";
    @Bean
    public CaffeineCache cacheSurfliesBuoys() {
        return new CaffeineCache(CACHE_NAME_SURFLIES_BUOY,
                Caffeine.newBuilder()
                        .expireAfterAccess(56, TimeUnit.MINUTES)
                        .build());
    }

    public static final String CACHE_NAME_ARTICLES = "ARTICLES";
    @Bean
    public CaffeineCache cacheArticles() {
        return new CaffeineCache(CACHE_NAME_ARTICLES,
                Caffeine.newBuilder()
                        .expireAfterAccess(1, TimeUnit.DAYS)
                        .build());
    }
}
