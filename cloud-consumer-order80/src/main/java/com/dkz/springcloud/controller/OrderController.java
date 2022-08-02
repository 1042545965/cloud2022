package com.dkz.springcloud.controller;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.service.feign.PaymentFeignService;
import com.dkz.springcloud.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/getById/{paymentId}")
    public Result<PaymentDto> getById(@PathVariable("paymentId") Long paymentId) {
        return paymentFeignService.getPaymentById(paymentId);
    }


    @GetMapping(value = "/getTimOutException/{paymentId}")
    public Result<PaymentDto> getTimOutException(@PathVariable("paymentId") Long paymentId) {
        return paymentFeignService.getTimOutException(paymentId);
    }


    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            // 设置超时线程的名字 , 并且添加超时时间是 3s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @GetMapping(value = "/getHystrixTimeOut/{paymentId}")
    String getHystrixTimeOut(@PathVariable("paymentId") Long paymentId){
        return paymentFeignService.getHystrixTimeOut(paymentId);
    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            // 设置超时线程的名字 , 并且添加超时时间是 3s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @GetMapping(value = "/getHystrixException/{paymentId}")
    String getHystrixException(@PathVariable("paymentId") Long paymentId){
        return paymentFeignService.getHystrixException(paymentId);
    }

    @GetMapping(value = "/getHystrixGlobalTimeOut/{paymentId}")
    @HystrixCommand
    String getHystrixGlobalTimeOut(@PathVariable("paymentId") Long paymentId){
        return paymentFeignService.getHystrixTimeOut(paymentId);
    }


    @GetMapping(value = "/getHystrixFeignTimeOut/{paymentId}")
    Result<String> getHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId){
        long start = System.currentTimeMillis();
        Result<String> result = paymentFeignService.getNoHystrixTimeOut(paymentId);
        System.out.println(System.currentTimeMillis() - start);
        return result;
    }

    public String paymentTimeOutFallbackMethod(Long paymentId){
        return"我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(T--T)o)";
    }

    // 下面是全局的fallback
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理信息，请稍后再试，l( ToT)/~~";
    }

}