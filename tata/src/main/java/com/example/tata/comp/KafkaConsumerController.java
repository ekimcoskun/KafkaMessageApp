package com.example.tata.comp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping(path = "/producer")
public class KafkaConsumerController {
    Object KafkaMessage;
    private static final Logger logger =
            LoggerFactory.getLogger(KafkaConsumerController.class);

    // public static void main(String[] args) {
    //     SpringApplication.run(KafkaConsumerController.class, args);
    // }


    @KafkaListener(topics = "${kafka.topic_name}", groupId = "${kafka.group_id}")
    public void listener(String message) {
        logger.info("Received message = {}", message);
        KafkaMessage=message;
    }

    @GetMapping(path = "/messages")
    public Object  kafkaMessage() {
        return KafkaMessage;
    }
}
