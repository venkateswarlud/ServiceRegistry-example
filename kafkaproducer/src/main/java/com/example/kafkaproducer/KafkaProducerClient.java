package com.example.kafkaproducer;

import org.apache.kafka.clients.producer.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class KafkaProducerClient {

    @RequestMapping(value = "/sendMessage/{name}", method = RequestMethod.GET)
    public void sendMesssage(@PathVariable("name") String message) {
        String TOPIC_NAME = "";
        String KEY_NAME = "key-1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, KEY_NAME, message);

        //1.Fire-and-forget Producer - no need to get the RecordMetaData
        //2. Synchronous  -- just need to get the RecordMetaData
        //3. Asynchronous -- No need to wait for response immediatly max.in.flight.requests.per.connection= 5 by default
        try {
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println("Message is sent to Partition no " + metadata.partition() + " and offset " + metadata.offset());
            System.out.println("SynchronousProducer Completed with success.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SynchronousProducer failed with an exception");
        } finally {
            producer.close();
        }
    }

}


