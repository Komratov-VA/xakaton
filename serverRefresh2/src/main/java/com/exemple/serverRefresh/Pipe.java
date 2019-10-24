package com.exemple.serverRefresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Pipe {
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory()
//    {
////        Redis redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//     RedisTemplate<String, String> redisTemplate()
//    {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        return redisTemplate;
//    }

    //
    public static void main(String[] args) {

        SpringApplication.run(Pipe.class, args);

//redis
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//       jedisConnectionFactory.getConnection();
//        ApplicationContext context = SpringApplication.run(Pipe.class,args);
//
//
//        StringRedisTemplate repository = context.getBean(StringRedisTemplate.class);


//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new_app");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        final StreamsBuilder builder = new StreamsBuilder();
//        RedisTemplate redisTemplate = new RedisTemplate();
//        KStream<String, String> source = builder.stream("kafka_exemple");
//        KStream<String, String> words = source.flatMapValues(new ValueMapper<String, Iterable<? extends String>>() {
//            @Override
//            public Iterable<String> apply(String s) {
//               try {
//                   userRepository.findById(s);
//
//
////                   }else return Arrays.asList("");
//               }
//               catch (NullPointerException e)
//               {
//                   userRepository.save(s);
//                   return Arrays.asList(s);
//               }
//                return Arrays.asList("");
//            }
//        });
//        words.to("kafka_exemple_json");
////        source.to("kafka_exemple_json");
//        final Topology topology = builder.build();
//        System.out.println(topology.describe());
//        final KafkaStreams streams = new KafkaStreams(topology, properties);
//        streams.start();


    }

}
