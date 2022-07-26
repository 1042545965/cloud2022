package com.dkz.springcloud.payment.controller;

import com.dkz.springcloud.payment.entity.Payment;
import com.dkz.springcloud.payment.entity.Result;
import com.dkz.springcloud.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 新增用户
     */
    @GetMapping(value = "/getById/{paymentId}")
    public Result<Payment> getById(@PathVariable Long paymentId) {
        return Result.ok(paymentService.getPaymentById(paymentId));
    }

}