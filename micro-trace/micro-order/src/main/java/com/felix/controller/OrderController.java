package com.felix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author felix
 * @desc some desc
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/get")
    public Object get(@RequestParam("id") String id){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        return objectNode.put("id", id);
    }
}
