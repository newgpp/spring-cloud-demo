package com.felix.feign.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.feign.OrderFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class OrderFeignFallbackFactory implements FallbackFactory<OrderFeignClient> {

    private static final Logger logger = LoggerFactory.getLogger(OrderFeignFallbackFactory.class);

    @Override
    public OrderFeignClient create(Throwable cause) {

        logger.error("OrderFeignClientFallback：", cause);

        return new OrderFeignClient() {
            @Override
            public Object getOrderExpress(String orderId) {
                return new ObjectMapper().createObjectNode().put("errMsg", cause.getMessage());
            }
        };
    }

}