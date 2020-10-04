package com.luizalabs.information.address.api.clients;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class ApplicationCacheConfig {
	
    @Value("${cache.expireAfterWrite}")
    private int cacheTimeExpire;


    @Bean
    public Caffeine caffeineConfig() {
    	return Caffeine.newBuilder().expireAfterWrite(cacheTimeExpire, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(final Caffeine caffeine) {
        final CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}