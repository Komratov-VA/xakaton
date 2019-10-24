//package com.exemple.serverRefresh;
//
//import io.lettuce.core.RedisClient;
//import io.lettuce.core.RedisURI;
//import io.lettuce.core.api.StatefulRedisConnection;
//import io.lettuce.core.resource.ClientResources;
//import io.lettuce.core.resource.DefaultClientResources;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//public class LettuceConfig {
//
//    @Bean(destroyMethod = "shutdown")
//    ClientResources clientResources() {
//        return DefaultClientResources.create();
//    }
//
//    @Bean(destroyMethod = "shutdown")
//    RedisClient redisClient(ClientResources clientResources) {
//
//        return RedisClient.create(clientResources, RedisURI.create("localhost", 6379));
//    }
//    @Bean
//    public RedisConnectionFactory lettuceConnectionFactory() {
//        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(Arrays.asList(cacheProps.nodes));
//        clusterConfig.setMaxRedirects(6);
//        LettuceConnectionFactory factory = new LettuceConnectionFactory(clusterConfig);
//        factory.setTimeout(30000);
//        factory.setShutdownTimeout(20000);
//        return factory;
//    }
//    @Bean(destroyMethod = "close")
//    StatefulRedisConnection<String, String> connection(RedisClient redisClient) {
//        return redisClient.connect();
//    }
//
//        @Bean
//        RedisTemplate<String, String> redisTemplate( StatefulRedisConnection statefulRedisConnection)
//    {
////       return statefulRedisConnection.reactive().get();
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory();
//        return redisTemplate;
//    }
//}