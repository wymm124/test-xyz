package com.miao.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * kafka 生产者 demo
 */
public class ProducerDemo {

    private final static String TOPIC_NAME = "topic-demo";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // kafka 参数
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 把发送的 key 序列化为字节数组
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 把发送的 value 序列化为字节数组
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 创建生产者客户端
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 创建消息: key 作用决定了消息发往哪个分区, value 是要发送的消息内容
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, "demoKey", "hello kafka 2");
        // 发送消息, 得到消息发送的元数据并输出
        RecordMetadata metadata = producer.send(producerRecord).get();
        System.out.println("同步方式发送消息结果: " + "topic-" + metadata.topic() + "; partition-" + metadata.partition() + "; offset-" + metadata.offset());
    }

}
