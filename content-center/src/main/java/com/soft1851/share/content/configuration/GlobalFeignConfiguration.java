package com.soft1851.share.content.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-29 22:40
 */
public class GlobalFeignConfiguration {
    @Bean
    public Logger.Level level() {
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}
