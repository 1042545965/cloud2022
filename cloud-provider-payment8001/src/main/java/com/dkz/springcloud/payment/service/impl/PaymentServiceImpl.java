package com.dkz.springcloud.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dkz.springcloud.payment.entity.Payment;
import com.dkz.springcloud.payment.mapper.PaymentMapper;
import com.dkz.springcloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {


    @Override
    public Payment getPaymentById(Long paymentId) {
        return baseMapper.getPaymentById(paymentId);
    }
}