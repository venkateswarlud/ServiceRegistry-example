package com.example.demo.config;

import com.example.demo.listener.TargetListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class kafkaConfig {

    @Autowired
    Environment environment;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(Integer.valueOf(environment.getProperty("kafka.concurrency")));
        factory.getContainerProperties().setPollTimeout(3000);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setRetryTemplate(new RetryTemplate() {
            {
                setRetryPolicy(new AlwaysRetryPolicy());
                setBackOffPolicy(new FixedBackOffPolicy() {
                    {
                        setBackOffPeriod(1000);
                    }
                });
            }
        });
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.broker"));
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, environment.getProperty("enable.auto.commit"));
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, environment.getProperty("auto.commit.interval.ms"));
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, environment.getProperty("session.timeout.ms"));
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("group.id"));
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, environment.getProperty("kafka.auto.offset.reset"));
      //  propsMap.put(ConsumerConfig.CLIENT_ID_CONFIG, environment.getProperty("kafka.client.id"));
        return propsMap;
    }


    @Bean
    public TargetListener listener(){
        return new TargetListener();
    }

}
