package com.techprimer.kafka.spring.listener;

import com.techprimer.kafka.spring.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafka_exemple",groupId = "group_id")
    public void consume(String message)
    {
        System.out.println("Consumer message:" +message);
    }

//    @KafkaListener(topics = "kafka_exemple_json",groupId= "group_json"
//    , containerFactory = "userKafkaListenerFactory")
//    public void consumeJson(User user)
//    {
//        System.out.println("Consumer message:" + user
//        );
//    }
}
