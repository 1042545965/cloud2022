package com.dkz.springcloud.controller;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.service.feign.PaymentFeignService;
import com.dkz.springcloud.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/getById/{paymentId}")
    public Result<PaymentDto> getById(@PathVariable("paymentId") Long paymentId) {
        return paymentFeignService.getPaymentById(paymentId);
    }


    @GetMapping(value = "/getTimOutException/{paymentId}")
    public Result<PaymentDto> getTimOutException(@PathVariable("paymentId") Long paymentId) {
        return paymentFeignService.getTimOutException(paymentId);
    }

}