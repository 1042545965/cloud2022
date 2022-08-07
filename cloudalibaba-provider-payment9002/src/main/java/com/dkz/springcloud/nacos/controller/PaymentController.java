package com.dkz.springcloud.nacos.controller;

import com.dkz.springcloud.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;


    @GetMapping(value = "/getById/{paymentId}")
    public Result<String> getById(@PathVariable("paymentId") Long paymentId) {

        return Result.ok("nacos hello port : " + port);
    }


}