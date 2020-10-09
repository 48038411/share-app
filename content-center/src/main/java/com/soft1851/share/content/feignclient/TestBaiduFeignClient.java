package com.soft1851.share.content.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Guorc
 */
@FeignClient(name = "baidu", url = "https://www.baidu.com")
public interface TestBaiduFeignClient {
    @GetMapping("")
    String index();
}
