package com.felix.feign.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.feign.ExpressFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class ExpressFeignFallbackFactory implements FallbackFactory<ExpressFeignClient> {

    private static final Logger logger = LoggerFactory.getLogger(ExpressFeignFallbackFactory.class);

    @Override
    public ExpressFeignClient create(Throwable cause) {

        logger.error("ExpressFeignClientFallback：", cause);

        return new ExpressFeignClient() {
            @Override
            public Object get(String orderId) {
                return new ObjectMapper().createObjectNode().put("errMsg", cause.getMessage());
            }
        };
    }

}