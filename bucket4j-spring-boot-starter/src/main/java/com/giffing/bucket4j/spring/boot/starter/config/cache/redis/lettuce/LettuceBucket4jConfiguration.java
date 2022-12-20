package com.giffing.bucket4j.spring.boot.starter.config.cache.redis.lettuce;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.giffing.bucket4j.spring.boot.starter.config.cache.SyncCacheResolver;
import com.giffing.bucket4j.spring.boot.starter.context.properties.Bucket4JBootProperties;

import io.github.bucket4j.redis.jedis.cas.JedisBasedProxyManager.JedisBasedProxyManagerBuilder;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import io.lettuce.core.RedisClient;
import redis.clients.jedis.JedisPool;

@Configuration
@ConditionalOnClass(LettuceBasedProxyManager.class)
@ConditionalOnBean(RedisClient.class)
@ConditionalOnProperty(prefix = Bucket4JBootProperties.PROPERTY_PREFIX, name = "cache-to-use", havingValue = "redis-lettuce", matchIfMissing = true)
public class LettuceBucket4jConfiguration {

	@Bean
	public SyncCacheResolver bucket4RedisResolver(RedisClient redisClient) {
		return new LettuceCacheResolver(redisClient);
	}
}