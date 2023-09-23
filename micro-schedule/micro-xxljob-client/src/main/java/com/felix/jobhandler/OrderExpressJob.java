package com.felix.jobhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.feign.OrderFeignClient;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class OrderExpressJob {

    private static final Logger log = LoggerFactory.getLogger(OrderExpressJob.class);

    @Resource
    private OrderFeignClient orderFeignClient;

    @XxlJob("getOrderJob")
    public void getOrder() {
        XxlJobHelper.log("XXL-JOB, GET ORDER EXPRESS.");
        try {
            String jobParam = XxlJobHelper.getJobParam();
            Object orderExpress = orderFeignClient.getOrderExpress(jobParam == null ? UUID.randomUUID().toString() : jobParam);
            String s = new ObjectMapper().writeValueAsString(orderExpress);
            XxlJobHelper.log("XXL-JOB, GET ORDER EXPRESS: {}", s);
            XxlJobHelper.handleSuccess(s);
        } catch (Exception e) {
            log.error("OrderExpressJob Error: ", e);
            XxlJobHelper.log(e);
            XxlJobHelper.handleFail(e.getMessage());
        }
    }
}
