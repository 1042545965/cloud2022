package com.dkz.springcloud.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentApplication9002 {

    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentApplication9002.class, args);
    }


}