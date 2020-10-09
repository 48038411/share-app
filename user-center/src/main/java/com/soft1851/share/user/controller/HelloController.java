package com.soft1851.share.user.controller;

import com.soft1851.share.user.common.ResponseResult;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-23 9:44
 */
@Slf4j
@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    //    @GetMapping(value = "/hello")
//    public String hello(){
//        return "This is user_center";
//    }
    @GetMapping(value = "/call/hello")
    public String call() {
        List<ServiceInstance> instances = discoveryClient.getInstances("content-center");
        int length = instances.size();
        Random random = new Random();
        int i = random.nextInt(length);
        String targetUrl = instances.get(i).getUri().toString() + "/notice/list";

//        String targetUrl = instances.stream()
//                .map( instance -> instance.getUri().toString() + "/hello")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例!"));
        log.info("请求的目标地址,{}", targetUrl);
        return restTemplate.getForObject(targetUrl, String.class);
    }

    @GetMapping(value = "/hello")
    public ResponseResult getRibbon() {
        log.info("我被调用了");
        List<ServiceInstance> instances = discoveryClient.getInstances("content-center");
        int length = instances.size();
        Random random = new Random();
        int i = random.nextInt(length);
        String targetUrl = instances.get(i).getUri().toString() + "/notice/list";

//        String targetUrl = instances.stream()
//                .map( instance -> instance.getUri().toString() + "/hello")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例!"));
        log.info("请求的目标地址,{}", targetUrl);
        return restTemplate.getForObject(targetUrl, ResponseResult.class);
    }
}
