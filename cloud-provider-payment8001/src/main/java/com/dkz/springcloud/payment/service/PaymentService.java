package com.dkz.springcloud.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.payment.entity.Payment;

public interface PaymentService extends IService<Payment> {
    PaymentDto getPaymentDtoById(Long paymentId);
}