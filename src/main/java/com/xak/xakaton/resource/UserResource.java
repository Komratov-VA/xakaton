package com.xak.xakaton.resource;

import com.xak.xakaton.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {
    @Autowired
  private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC =
            "kafka_exemple";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name){
        while (true)
        {
            kafkaTemplate.send(TOPIC,
                    new User(name, "Tecnology", 12000L));
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        return "Published succes";
    }
}
