package com.dkz.springcloud.payment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("payment")
public class Payment {
    private Long id;
    private String serial;
}