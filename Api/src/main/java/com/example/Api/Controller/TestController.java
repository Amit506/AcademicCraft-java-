package com.example.Api.Controller;

import com.example.Core.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller("apiTestController")
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG = LogManager.getLogger(TestController.class);

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/kafka")
    ResponseEntity kafkaTest(HttpServletRequest httpServletRequest){
        try {

        kafkaProducer.sendMessage("rnmqsqnw-test","Hii testing");
        return  ResponseEntity.ok("success");

        } catch (Exception e) {
            LOG.info("error ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
