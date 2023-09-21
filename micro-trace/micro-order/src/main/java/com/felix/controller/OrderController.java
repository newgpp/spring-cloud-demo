package com.felix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.felix.feign.ExpressFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author felix
 * @desc some desc
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private ExpressFeignClient expressFeignClient;

    @GetMapping("/get")
    public Object get(@RequestParam("orderId") String orderId){

        log.trace("====> /order/get trace, orderId={}", orderId);
        log.debug("====> /order/get debug, orderId={}", orderId);
        log.info("====> /order/get info, orderId={}", orderId);
        log.warn("====> /order/get warn, orderId={}", orderId);
        log.error("====> /order/get error, orderId={}", orderId);

        Object orderExpress = expressFeignClient.get(orderId);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        return objectNode.put("orderId", orderId).putPOJO("express", orderExpress);
    }
}
