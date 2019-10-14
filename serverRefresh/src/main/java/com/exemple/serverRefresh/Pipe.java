package com.exemple.serverRefresh;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisHash;

import java.util.Arrays;
import java.util.Properties;

import static org.springframework.boot.SpringApplication.*;


@SpringBootApplication
public class Pipe
{


    public static void main(String[] args) {
//redis




//        UrlShoortenerResourse repository = context.getBean(UrlShoortenerResourse.class);


        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new_app");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        final StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream("kafka_exemple");
        KStream<String, String> words = source.flatMapValues(new ValueMapper<String, Iterable<? extends String>>() {
            @Override
            public Iterable<String> apply(String s) {
//                repository.save(s);
                return Arrays.asList(s.split("\\W+"));
            }
        });
        words.to("kafka_exemple_json");
//        source.to("kafka_exemple_json");
        final Topology topology = builder.build();
        System.out.println(topology.describe());
        final KafkaStreams streams = new KafkaStreams(topology, properties);
        streams.start();
        ApplicationContext context = SpringApplication.run(Pipe.class, args);

    }

}
