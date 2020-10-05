package com.kafkaconsumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class KafkaConsumerClient {

    @RequestMapping(value = "/sendMessage/{name}", method = RequestMethod.GET)
    public void sendMesssage(@PathVariable("name") String name) {
       /* String TOPIC_NAME = "";
        String KEY_NAME = "key-1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, KEY_NAME, name);
        producer.send(producerRecord);
        producer.close();
        System.out.println("SimpleProducer Completed.");*/
    }
}
