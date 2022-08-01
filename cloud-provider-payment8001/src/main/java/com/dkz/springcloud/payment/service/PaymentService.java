package com.dkz.springcloud.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.payment.entity.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

public interface PaymentService extends IService<Payment> {
    PaymentDto getPaymentDtoById(Long paymentId);

    String getHystrixTimeOut(Long paymentId);

    String getHystrixException(Long paymentId);
}