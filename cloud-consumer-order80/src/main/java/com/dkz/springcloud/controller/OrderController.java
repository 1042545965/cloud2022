package com.dkz.springcloud.controller;

import com.dkz.springcloud.dto.PaymentDto;
import com.dkz.springcloud.service.feign.PaymentFeignService;
import com.dkz.springcloud.service.feign.TestServerFeignService;
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

    @Autowired
    private TestServerFeignService testServerFeignService;


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
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })
    @GetMapping(value = "/getHystrixTimeOut/{paymentId}")
    Result<String> getHystrixTimeOut(@PathVariable("paymentId") Long paymentId){
        return paymentFeignService.getDefaultHystrixFeignTimeOut(paymentId);
    }
    // 这个注解的方式在 开启 feign 的 hystrix 的配置后 不在生效
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            // 设置超时线程的名字 , 并且添加超时时间是 3s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @GetMapping(value = "/getHystrixException/{paymentId}")
    Result<String> getHystrixException(@PathVariable("paymentId") Long paymentId){
        return paymentFeignService.getHystrixException(paymentId);
    }

    @GetMapping(value = "/getHystrixGlobalTimeOut/{paymentId}")
    @HystrixCommand
    Result<String> getHystrixGlobalTimeOut(@PathVariable("paymentId") Long paymentId){
        return paymentFeignService.getHystrixTimeOut(paymentId);
    }


    @GetMapping(value = "/getHystrixFeignTimeOut/{paymentId}")
    Result<String> getHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId){
        long start = System.currentTimeMillis();
        Result<String> result = paymentFeignService.getNoHystrixTimeOut(paymentId);
        System.out.println("getHystrixFeignTimeOut----" + (System.currentTimeMillis() - start));
        return result;
    }

    @GetMapping(value = "/getDefaultHystrixFeignTimeOut/{paymentId}")
    Result<String> getDefaultHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId){
        long start = System.currentTimeMillis();
        Result<String> result = paymentFeignService.getDefaultHystrixFeignTimeOut(paymentId);
        System.out.println("getHystrixFeignTimeOut----" + (System.currentTimeMillis() - start));
        return result;
    }

    @GetMapping(value = "/getInterfaceHystrixFeignTimeOut/{paymentId}")
    Result<String> getInterfaceHystrixFeignTimeOut(@PathVariable("paymentId") Long paymentId){
        long start = System.currentTimeMillis();
        Result<String> result = testServerFeignService.getInterfaceHystrixFeignTimeOut(paymentId);
        System.out.println("getInterfaceHystrixFeignTimeOut----" + (System.currentTimeMillis() - start));
        return result;
    }

    public Result<String> paymentTimeOutFallbackMethod(Long paymentId){
        return Result.ok("我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(T--T)o)");
    }

    // 下面是全局的fallback
    public Result<String> payment_Global_FallbackMethod(){
        return Result.ok("我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(T--T)o)");
    }

}