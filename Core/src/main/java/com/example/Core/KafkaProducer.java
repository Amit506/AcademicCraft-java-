package com.example.Core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger LOG = LogManager.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        try {
           LOG.info("default : "+kafkaTemplate.getDefaultTopic());
            LOG.info("topic {}, message {}",topic,message);
            kafkaTemplate.send(topic, message);
        } catch (Exception e) {
            LOG.error("error : ", e);

        }
    }

}