package com.felix.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author felix
 * @desc some desc
 */
@FeignClient(name = "micro-express")
public interface ExpressFeignClient {

    @GetMapping("/express/get")
    public Object get(@RequestParam("orderId") String orderId);
}
