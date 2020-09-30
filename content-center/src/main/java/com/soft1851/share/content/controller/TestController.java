package com.soft1851.share.content.controller;

import com.soft1851.share.content.domain.dto.UserDTO;
import com.soft1851.share.content.feignclient.TestBaiduFeignClient;
import com.soft1851.share.content.feignclient.TestUserCenterFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    private TestUserCenterFeignClient testUserCenterFeignClient;
    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;
    @GetMapping("/discovery")
    public List<ServiceInstance> get(){
        return discoveryClient.getInstances("user-center");
    }
    @GetMapping("/hello")
    public String hello(){
        return "This is content_hello";
    }
    @GetMapping(value = "/test-q")
    public UserDTO query(UserDTO userDTO){
        return testUserCenterFeignClient.query(userDTO);
    }
    @GetMapping(value = "/baidu")
    public String index(){
        return testBaiduFeignClient.index();
    }
    @GetMapping(value = "call/ribbon")
    public String getRibbon(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://user-center/user/info/{name}", String.class,name);
    }
}
