package com.example.Auth.Controller;

import com.example.Auth.ResponseModel.BaseResponseBuilder;
import com.example.Auth.Service.TestService;
import com.example.Core.RedisService;
import com.example.Core.startup.RedisConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG = LogManager.getLogger(TestController.class);

    @Autowired
    TestService testService;
    @Autowired

    RedisService redisService;

    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    @PostMapping("/addUser")
    ResponseEntity attachGroupToPermission(HttpServletRequest httpServletRequest, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
        try {
            BaseResponseBuilder baseResponseBuilder = testService.addTestUser(username, password, email);
            return ResponseEntity.status(HttpStatus.valueOf(200)).body(baseResponseBuilder);
        } catch (Exception e) {
            LOG.info("error ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    @GetMapping("/ping")
    ResponseEntity test(HttpServletRequest httpServletRequest) {
        try {
            redisService.setValue("redis_test", "success");
            LOG.info(redisService.getValue("test"));
            return ResponseEntity.status(HttpStatus.valueOf(200)).body("Success");
        } catch (Exception e) {
            LOG.info("error ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
