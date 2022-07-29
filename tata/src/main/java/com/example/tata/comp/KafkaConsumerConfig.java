package com.example.tata.comp;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

public class KafkaConsumerConfig {
    @Value(value = "${kafka.server_endpoint}")
    private String kafkaServerEndpoint;

    @Value(value = "${kafka.group_id}")
    private String kafkaGroupId;


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {

        Map<String, Object> map = new HashMap<>();

        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(map);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListner() {

        ConcurrentKafkaListenerContainerFactory<String, String> obj =
                new ConcurrentKafkaListenerContainerFactory<>();
        obj.setConsumerFactory(consumerFactory());
        return obj;
    }
}
