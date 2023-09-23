package com.felix.feign;

import com.felix.feign.fallback.OrderFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author felix
 * @desc some desc
 */
@FeignClient(name = "micro-order", fallbackFactory = OrderFeignFallbackFactory.class)
public interface OrderFeignClient {

    @GetMapping("/order/get")
    public Object getOrderExpress(@RequestParam("orderId") String orderId);
}
