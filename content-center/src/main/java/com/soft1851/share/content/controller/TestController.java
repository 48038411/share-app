package com.soft1851.share.content.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.soft1851.share.content.domain.dto.UserDTO;
import com.soft1851.share.content.feignclient.TestBaiduFeignClient;
import com.soft1851.share.content.feignclient.TestUserCenterFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-23 8:38
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    @Autowired
    private TestUserCenterFeignClient testUserCenterFeignClient;
    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping("/discovery")
    public List<ServiceInstance> get() {
        return discoveryClient.getInstances("user-center");
    }

    @GetMapping("/hello")
    public String hello() {
        return "This is content_hello";
    }

    @GetMapping(value = "/test-q")
    public UserDTO query(UserDTO userDTO) {
        return testUserCenterFeignClient.query(userDTO);
    }

    @GetMapping(value = "/baidu")
    public String index() {
        return testBaiduFeignClient.index();
    }

    @GetMapping(value = "/call/ribbon/{id}")
    public String getRibbon(@PathVariable Integer id) {
        return restTemplate.getForObject("http://user-center/users/{id}", String.class, id);
    }

    @GetMapping(value = "/users/{id}")
    public ListenableFuture<ResponseEntity<String>> getUser(@PathVariable Integer id) {
        return asyncRestTemplate.getForEntity("http://user-center/users/{id}", String.class, id);
    }

    @GetMapping("byResources")
    @SentinelResource(value = "hello", blockHandler = "handleException")
    public String byResources() {
        return "按名称限流";
    }

    public String handleException(BlockException blockException) {
        return "服务不可用";
    }
}
