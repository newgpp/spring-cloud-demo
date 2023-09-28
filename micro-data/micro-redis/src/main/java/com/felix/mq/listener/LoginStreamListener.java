package com.felix.mq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;

import javax.annotation.Resource;
import java.util.Map;

public class LoginStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    private static final Logger log = LoggerFactory.getLogger(LoginStreamListener.class);

    @Value("${redis.stream.login.key:login-events}")
    private String streamLoginKey;
    @Value("${redis.stream.login.group:login-group-1}")
    private String streamLoginGroup;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onMessage(MapRecord<String, String, String> record) {

        log.info("stream listener received record, key: {}, group: {}, record: {}", streamLoginKey
                , streamLoginGroup, record);

        Map<String, String> message = record.getValue();
        try {
            String value = objectMapper.writeValueAsString(message);
            log.info("onMessage: {}", value);
        } catch (Exception e) {
            log.error("consume message error: ", e);
        }
        RecordId recordId = record.getId();
        redisTemplate.opsForStream().acknowledge(streamLoginKey, streamLoginGroup, recordId);

        log.info("stream listener ack record, key: {}, group: {}, recordId: {}", streamLoginKey
                , streamLoginGroup, recordId);
    }
}
