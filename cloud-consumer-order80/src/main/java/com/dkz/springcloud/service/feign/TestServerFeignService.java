package com.dkz.springcloud.service.feign;

import com.dkz.springcloud.service.impl.PaymentFeignFallbackService;
import com.dkz.springcloud.service.impl.TestServerFeignFallbackService;
import com.dkz.springcloud.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = TestServerFeignFallbackService.class , contextId = "testServerFeign" )
public interface TestServerFeignService {

    @GetMapping(value = "/payment/getInterfaceHystrixFeignTimeOut/{paymentId}")
    Result<String> getInterfaceHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId);
}