package com.exemple.serverRefresh;

import com.exemple.serverRefresh.redis.UserRepository;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

@Component
public class kafka {

    private UserRepository userRepository;

    public kafka(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory()
//    {
////        Redis redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
//        return new JedisConnectionFactory();
//    }
//@Bean
//    RedisTemplate<String, String> redisTemplate()
//    {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        return redisTemplate;
//    }


    @Bean
    public StreamsBuilderFactoryBean commonDSLBuilder() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new_app");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsConfig streamsConfig = new StreamsConfig(properties);
        StreamsBuilderFactoryBean streamsBuilder = new StreamsBuilderFactoryBean(streamsConfig);
//        streamsBuilder.setSingleton(Boolean.FALSE);
        return streamsBuilder;
    }
    @Bean
    public KStream<String, String> startProcessing(StreamsBuilder builder)
    {

////        UserRepositoryImpl userRepository = new UserRepositoryImpl();
//        Properties properties = new Properties();
////    private Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new_app");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    //    final StreamsBuilder builder = new StreamsBuilder();
//        RedisTemplate redisTemplate = new RedisTemplate();
        KStream<String, String> source = builder.stream("kafka_exemple", Consumed.with(Serdes.String(),Serdes.String()));
        source.
        filter(((key, value) -> shange(value)))
        .to("kafka_exemple_json", Produced.with(Serdes.String(),Serdes.String()));
//        KStream<String, String> words = source.flatMapValues(new ValueMapper<String, Iterable<? extends String>>() {
//            @Override
//            public Iterable<String> apply(String s) {
//                try {
//                    userRepository.findById(s);
//
//
////                   }else return Arrays.asList("");
//                }
//                catch (NullPointerException e)
//                {
//                    userRepository.save(s);
//                    return Arrays.asList(s);
//                }
//                return Arrays.asList("");
//            }
//        });
//        words.to("kafka_exemple_json");
        //        source.to("kafka_exemple_json");
//        final Topology topology = builder.build();

//        final KafkaStreams streams = new KafkaStreams(topology, properties);
        //streams.start()
      return   source;
    }
    public boolean shange(String value)
    {//1571819089711
        System.out.println(new Date().getTime()+"do");
        if (userRepository.findById(value) !=null)
        {
            System.out.println(new Date().getTime()+"if");
            return false;
        }
        else
            userRepository.save(value);
        System.out.println(new Date().getTime()+"no if");
        return true;
    }

}
