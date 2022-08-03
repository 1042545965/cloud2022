package com.dkz.springcloud.service.feign;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.service.impl.PaymentFeignFallbackService;
import com.dkz.springcloud.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE" , fallback = PaymentFeignFallbackService.class , contextId = "paymentFeign")
public interface PaymentFeignService {


    @GetMapping(value = "/payment/getById/{paymentId}")
    Result<PaymentDto> getPaymentById(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getTimOutException/{paymentId}")
    Result<PaymentDto> getTimOutException(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getHystrixOk/{paymentId}")
    Result<String> getHystrixOk(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getHystrixTimeOut/{paymentId}")
    Result<String> getHystrixTimeOut(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getHystrixException/{paymentId}")
    Result<String> getHystrixException(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getNoHystrixTimeOut/{paymentId}")
    Result<String> getNoHystrixTimeOut(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getNoHystrixTimeOut/{paymentId}")
    Result<String> getDefaultHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId);

    @GetMapping(value = "/payment/getInterfaceHystrixFeignTimeOut/{paymentId}")
    Result<String> getInterfaceHystrixFeignTimeOut(@PathVariable("paymentId")Long paymentId);
}