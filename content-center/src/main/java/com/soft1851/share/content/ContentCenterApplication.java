package com.soft1851.share.content;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import com.soft1851.share.content.configuration.UserCenterFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Guorc
 */
@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.share.content.dao")
@EnableFeignClients //(defaultConfiguration = UserCenterFeignConfiguration.class)
@EnableGlobalDispose
public class ContentCenterApplication {

    public static void main(String[] args) {
//        MD5Service md5Service = new MD5Service();
//        md5Service.getMD5()
        SpringApplication.run(ContentCenterApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
