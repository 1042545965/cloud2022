package com.dkz.springcloud.service.impl;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.service.feign.PaymentFeignService;
import com.dkz.springcloud.utils.Result;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignFallbackService implements PaymentFeignService {
    @Override
    public Result<PaymentDto> getPaymentById(Long paymentId) {
        return null;
    }

    @Override
    public Result<PaymentDto> getTimOutException(Long paymentId) {
        return null;
    }

    @Override
    public Result<String> getHystrixOk(Long paymentId) {
        return null;
    }

    @Override
    public Result<String>  getHystrixTimeOut(Long paymentId) {
        return Result.ok("-----PaymentFeignFallbackService fall back getHystrixException ,o(T- T)o");
    }

    @Override
    public Result<String>  getHystrixException(Long paymentId) {
        return Result.ok("-----PaymentFeignFallbackService fall back getHystrixException ,o(T- T)o");
    }

    @Override
    public Result<String> getNoHystrixTimeOut(Long paymentId) {
        return Result.ok("-----PaymentFeignFallbackService fall back getNoHystrixTimeOut ,o(T- T)o");
    }

    @Override
    public Result<String> getDefaultHystrixFeignTimeOut(Long paymentId) {
        return Result.ok("-----PaymentFeignFallbackService fall back getDefaultHystrixFeignTimeOut ,o(T- T)o");
    }

    @Override
    public Result<String> getInterfaceHystrixFeignTimeOut(Long paymentId) {
        return Result.ok("-----PaymentFeignFallbackService fall back getInterfaceHystrixFeignTimeOut ,o(T- T)o");
    }
}
