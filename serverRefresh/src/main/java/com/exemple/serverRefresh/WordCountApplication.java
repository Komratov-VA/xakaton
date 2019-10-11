package com.exemple.serverRefresh;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Arrays;
import java.util.Properties;

public class WordCountApplication {

    public static void main(final String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");//StreamsConfig.APPLICATION_ID_CONFIG
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:9092");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> textLines = builder.stream("TextLinesTopic");
        KStream<String, String> textLines = builder.stream("kafka_exemple");
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(new ValueMapper<String, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(String textLine) {
                        return Arrays.asList(textLine.toLowerCase().split("\\W+"));//разделитель любой символ кроме буквы цифры
                    }
                })
                .groupBy(new KeyValueMapper<String, String, String>() {
                    @Override
                    public String apply(String key, String word) {
                        return word;
                    }
                })
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));


//        wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
        wordCounts.toStream().to("kafka_exemple_json", Produced.with(Serdes.String(), Serdes.Long()));
        System.out.println("seva"+wordCounts);
        System.out.println("seva");
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }

}
