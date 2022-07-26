package com.dkz.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class PaymentController {


    /**
     * 新增用户
     */
    @GetMapping(value = "/getById/{paymentId}")
    public String getById(@PathVariable Long paymentId) {
        return null;
    }

}