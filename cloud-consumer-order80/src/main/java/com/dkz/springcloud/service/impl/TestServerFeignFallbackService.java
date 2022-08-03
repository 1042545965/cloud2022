package com.dkz.springcloud.service.impl;

import com.dkz.springcloud.service.feign.TestServerFeignService;
import com.dkz.springcloud.utils.Result;
import org.springframework.stereotype.Component;

@Component
public class TestServerFeignFallbackService implements TestServerFeignService {

    @Override
    public Result<String> getInterfaceHystrixFeignTimeOut(Long paymentId) {
        return Result.ok("-----PaymentFeignFallbackService fall back getInterfaceHystrixFeignTimeOut ,o(T- T)o");
    }
}
