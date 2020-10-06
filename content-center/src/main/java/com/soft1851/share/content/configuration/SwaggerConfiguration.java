package com.soft1851.share.content.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Guorc
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "share-app Swagger 文档",
                "github地址 https://github.com/48038411/micro-service/share-app",
                "API V1.0",
                "Terms of service",
                new Contact("郭瑞昌","https://guoruichang.cn","48038411@qq.com"),
                "Apache","http://www.apache.org/", Collections.emptyList());

    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.share.content"))
                .build()
                .apiInfo(apiInfo());
    }
}
