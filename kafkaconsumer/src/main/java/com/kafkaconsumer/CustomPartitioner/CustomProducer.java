package com.kafkaconsumer.CustomPartitioner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class CustomProducer {

    @RequestMapping(value = "/sendMessage/{name}", method = RequestMethod.GET)
    public void sendMesssage(@PathVariable("name") String name) {

        String topicName = "SensorTopic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "SensorPartitioner");
        props.put("speed.sensor.name", "TSS");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>(topicName, "SSP" + i, "500" + i));

        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<>(topicName, "TSS", "500" + i));

        producer.close();
        System.out.println("SimpleProducer Completed.");
    }
}
