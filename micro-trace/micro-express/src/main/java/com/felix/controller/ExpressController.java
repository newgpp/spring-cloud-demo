package com.felix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author felix
 * @desc some desc
 */
@RestController
@RequestMapping("/express")
public class ExpressController {

    private static final Logger log = LoggerFactory.getLogger(ExpressController.class);

    @GetMapping("/get")
    public Object get(@RequestParam("orderId") String orderId){

        log.info("====> /express/get, orderId={}", orderId);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        return objectNode.put("orderId", orderId)
                .put("expressId", "SF0000001")
                .put("expressName", "SF");
    }
}
