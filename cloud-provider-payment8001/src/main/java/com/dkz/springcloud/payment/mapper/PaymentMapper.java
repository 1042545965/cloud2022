package com.dkz.springcloud.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dkz.springcloud.payment.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {


    Payment getPaymentById(@Param("paymentId") Long paymentId);
}