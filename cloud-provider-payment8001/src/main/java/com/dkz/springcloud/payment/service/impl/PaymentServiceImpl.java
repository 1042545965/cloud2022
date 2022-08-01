package com.dkz.springcloud.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.payment.entity.Payment;
import com.dkz.springcloud.payment.mapper.PaymentMapper;
import com.dkz.springcloud.payment.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {


    @Override
    public PaymentDto getPaymentDtoById(Long paymentId) {
        Payment payment = baseMapper.getPaymentById(paymentId);
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(payment, paymentDto);
        return paymentDto;
    }


    @Override
    @HystrixCommand(fallbackMethod = "getHystrixTimeOutHandler", commandProperties = {
            // 设置超时线程的名字 , 并且添加超时时间是 3s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String getHystrixTimeOut(Long paymentId) {
        try {
            TimeUnit.SECONDS.sleep(6L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池 ：" + Thread.currentThread().getName() + "paymentId :" + paymentId;
    }


    public String getHystrixTimeOutHandler(Long paymentId) {
        return "线程池 ：" + Thread.currentThread().getName() + "paymentId :" + paymentId + "getHystrixTimeOutHandler";
    }

    @Override
    @HystrixCommand(fallbackMethod = "getHystrixTimeOutHandler", commandProperties = {
            // 设置超时线程的名字 , 并且添加超时时间是 3s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String getHystrixException(Long paymentId) {
        int age  = 10 / 0;
        return "线程池 ：" + Thread.currentThread().getName() + "paymentId :" + paymentId;
    }

}