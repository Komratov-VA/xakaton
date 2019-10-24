package com.exemple.serverRefresh;

import com.exemple.serverRefresh.redis.UserRepository;
import com.exemple.serverRefresh.redis.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
//classpath:path/
//@ImportResource("to/applicationContext.xml")
@ComponentScan(basePackageClasses = UserRepositoryImpl.class)
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory()
    {
//        Redis redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory();
    }
    @Bean
    RedisTemplate<String, String> redisTemplate()
    {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
