package com.felix;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.time.Duration;
import java.util.Properties;

/**
 * @author felix
 * @desc some desc
 */
public class KafkaProducerTest {


    @Test
    public void send_message_should_success() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.159.111:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String topic = "my-topic";
        ProducerRecord<String, String> message = new ProducerRecord<>(topic, "kkkkkk", "111111");
        producer.send(message, (metadata, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                System.out.println("The offset of the record we just sent is: " + metadata.offset());
            }
        });
        producer.close(Duration.ofSeconds(1L));
    }


}
