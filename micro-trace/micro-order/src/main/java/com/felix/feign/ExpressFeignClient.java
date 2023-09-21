package com.felix.feign;

import com.felix.feign.fallback.ExpressFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author felix
 * @desc some desc
 */
@FeignClient(name = "micro-express", fallbackFactory = ExpressFeignFallbackFactory.class)
public interface ExpressFeignClient {

    @GetMapping("/express/get")
    public Object get(@RequestParam("orderId") String orderId);
}
