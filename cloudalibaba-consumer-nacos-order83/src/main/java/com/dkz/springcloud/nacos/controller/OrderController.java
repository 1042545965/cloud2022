package com.dkz.springcloud.nacos.controller;

import com.dkz.springcloud.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@RefreshScope //支持 Nacos的动态刷新功能。
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value( "${service-url.nacos-user-service}")
    private String serverURL;

    @Value( "${config.info:configInfo}")
    private String configInfo;


    @GetMapping(value = "/getById/{paymentId}")
    public Result<String> getById(@PathVariable("paymentId") Long paymentId) {
        String nacosString = restTemplate.getForObject(serverURL + "/payment/getById/" + paymentId, String.class);
        return Result.ok("nacos hello nacosString : " + nacosString);
    }



    @GetMapping( "/config/info")
    public Result<String> getConfigInfo() {
        return Result.ok(configInfo);
    }


}