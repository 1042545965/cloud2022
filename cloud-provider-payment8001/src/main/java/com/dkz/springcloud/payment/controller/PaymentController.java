package com.dkz.springcloud.payment.controller;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.payment.service.PaymentService;
import com.dkz.springcloud.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<PaymentDto> getById(@PathVariable("paymentId") Long paymentId) {
        PaymentDto paymentDto = paymentService.getPaymentDtoById(paymentId);
        return Result.ok(paymentDto);
    }

}