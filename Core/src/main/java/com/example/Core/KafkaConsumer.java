package com.example.Core;

import com.example.Core.startup.DataSourceConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOG = LogManager.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
    public void listen(String message) {
       LOG.info("Received Message: {}", message);
    }
}
