package com.miao.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * kafka 消费者 demo
 */
public class ConsumerDemo {

    private final static String TOPIC_NAME = "topic-demo";

    private final static String CONSUMER_GROUP_NAME = "testGroup";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 消费分组名
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);
        // key、value 序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 创建消费者客户端
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 消费者订阅主题列表
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true) {
            // poll(): 拉取消息的长轮询
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000).toMillis());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("收到消息：partition = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }

}
