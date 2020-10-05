package com.example.demo.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class TargetListener implements AcknowledgingMessageListener {

    @Override
    public void onMessage(ConsumerRecord data, Acknowledgment acknowledgment) {


        if(data == null || data.value() == null || data.value().toString().isEmpty()){
            acknowledgment.acknowledge();
            return;
        }

        System.out.println("Data from the kafka -------------"+data);
        acknowledgment.acknowledge();

    }

    @Override
    public void onMessage(Object obj){
        System.out.println("Data from the kafka ------------"+obj);
    }
}
