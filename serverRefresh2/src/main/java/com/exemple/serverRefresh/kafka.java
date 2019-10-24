package com.exemple.serverRefresh;

import com.exemple.serverRefresh.redis.UserRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

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
//{
//    List<String> list = new ArrayList<>();
//    long time = System.currentTimeMillis();
//    while (System.currentTimeMillis() - time <100)
//    {
//        list.add(message);
//        return list;
//    }
//    list.add(message);
//
////    return
//}
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
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private static final String TOPIC =
            "kafka_exemple_json";
    @Bean
    public KStream<String, String> startProcessing(StreamsBuilder builder) {

////        UserRepositoryImpl userRepository = new UserRepositoryImpl();
//        Properties properties = new Properties();
////    private Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new_app");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //    final StreamsBuilder builder = new StreamsBuilder();
//        RedisTemplate redisTemplate = new RedisTemplate();
        int i = 4;
        System.out.println("na4alo");
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        List<String> list = new ArrayList<>();
        ConcurrentSkipListSet<String> hashSet = new ConcurrentSkipListSet<>();
        LinkedBlockingQueue<String> batchQueue = new LinkedBlockingQueue<String>(i);
        KStream<String, String> source = builder.stream("kafka_exemple", Consumed.with(Serdes.String(), Serdes.String()));
        source.process(new ProcessorSupplier<String, String>() {
            @Override
            public Processor<String, String> get() {
                return new AbstractProcessor<String, String>() {
                    @Override
                    public void process(String key, String value) {
                        hashSet.add(value);
//                        batchQueue.add(value);
                        System.out.println("privetNet" + hashSet.toString());
                        if (hashSet.size() > 3) {
                            for (String str : hashSet
                            ) {
                                System.out.println(hashSet.size());
                                if (userRepository.putif(str)) {

                                    System.out.println(hashSet.size());
                                    threadPool.submit(new RateTask(str));
                                }
//                                System.out.println(hashSet.size());
//                                if (userRepository.putif(str)) {
//                                    System.out.println("if"+str+"size"+hashSet.size());
////                                    list.add(value);
//                                    Thread t = new Thread(() -> {
//                                        System.out.println("runnavle");
//                                        kafkaTemplate.send(TOPIC,
//                                                str);
//                                        // put whatever code you want to run inside the thread here.
//                                    });
//                                    t.start();
//
//                                }
                            }
                            hashSet.clear();
                        }
                    }

                };
            }
        });
        System.out.println("end");
        return source;
    }
    private class RateTask implements Callable<String>
    {
        private String str;

        RateTask(String str)
        {
            this.str = str;
        }

        @Override
        public String call() throws Exception
        {
            kafkaTemplate.send(TOPIC, str);
            return "1";
        }
    }

}
//                        if(batchQueue.remainingCapacity() == 0)
//                        {
//                            for (String st:batchQueue
//                                 ) {
//                                if(userRepository.putif(value)){
////                                    list.add(value);
//                                    kafkaTemplate.send(TOPIC,
//                                            value);
//                                }
//                            }
//
////                            kafkaTemplate.send(TOPIC,
////                                    value+new Random().nextInt(10));
//                            System.out.println("privet"+batchQueue.toString());
//
//                            batchQueue.clear();

//        source.to("kafka_exemple_json", Produced.with(Serdes.String(),Serdes.String()));
//        filter(((key, value) -> shange(value)))
//        .to("kafka_exemple_json", Produced.with(Serdes.String(),Serdes.String()));
                               // конец
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

//    public boolean shange(String value)
//    {//1571819089711
//        System.out.println(System.currentTimeMillis()+"do");
////        userRepository.putif(value);
////        if (userRepository.findById(value) !=null)
//        if (userRepository.putif(value))
//        {
//            System.out.println(System.currentTimeMillis()+"if");
//            return false;
//        }
////        else
////            userRepository.save(value);
//        System.out.println(System.currentTimeMillis()+"no if");
//        return true;
//    }

