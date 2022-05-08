package com.kafka.spring.boot.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic-demo", groupId = "MyGroup1")
    public void listenGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("接收到的消息");
        System.out.println(value);
        System.out.println(record);
        System.out.println("消息接收完毕");
        // 手动提交 offset
        ack.acknowledge();
    }

}
