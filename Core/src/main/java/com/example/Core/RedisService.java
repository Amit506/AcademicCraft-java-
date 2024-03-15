package com.example.Core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value, @NonNull Long ttlInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, ttlInSeconds, TimeUnit.MILLISECONDS);
    }

    public void setValue(String key, String value) {
        long ttl = 604800 * 1000L;
        stringRedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.MILLISECONDS);
    }

    public Object getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
    }

}
