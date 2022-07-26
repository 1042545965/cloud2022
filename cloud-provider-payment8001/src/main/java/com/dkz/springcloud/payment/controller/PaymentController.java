package com.dkz.springcloud.payment.controller;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.payment.service.PaymentService;
import com.dkz.springcloud.utils.Result;
import java.util.concurrent.TimeUnit;
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


    @GetMapping(value = "/getById/{paymentId}")
    public Result<PaymentDto> getById(@PathVariable("paymentId") Long paymentId) {
        PaymentDto paymentDto = paymentService.getPaymentDtoById(paymentId);
        return Result.ok(paymentDto);
    }

    @GetMapping(value = "/getTimOutException/{paymentId}")
    Result<PaymentDto> getTimOutException(@PathVariable("paymentId") Long paymentId){
        try {
            TimeUnit.SECONDS.sleep(6L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.ok(new PaymentDto());
    }

    @GetMapping(value = "/getHystrixOk/{paymentId}")
    Result<String> getHystrixOk(@PathVariable("paymentId") Long paymentId){
        String resultString = "线程池 ：" + Thread.currentThread().getName() + "paymentId :" + paymentId;
        return Result.ok(resultString);
    }

    @GetMapping(value = "/getHystrixTimeOut/{paymentId}")
    Result<String> getHystrixTimeOut(@PathVariable("paymentId") Long paymentId){
        return Result.ok(paymentService.getHystrixTimeOut(paymentId));
    }

    @GetMapping(value = "/getHystrixException/{paymentId}")
    Result<String> getHystrixException(@PathVariable("paymentId") Long paymentId){
        return Result.ok(paymentService.getHystrixException(paymentId));
    }


    @GetMapping(value = "/getNoHystrixTimeOut/{paymentId}")
    Result<String> getNoHystrixTimeOut(@PathVariable("paymentId") Long paymentId){
        try {
            TimeUnit.SECONDS.sleep(6L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.ok("线程池 ：" + Thread.currentThread().getName() + "paymentId :" + paymentId);
    }

    @GetMapping(value = "/getInterfaceHystrixFeignTimeOut/{paymentId}")
    Result<String> getInterfaceHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId){
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.ok("线程池 ：" + Thread.currentThread().getName() + "paymentId :" + paymentId);
    }

}