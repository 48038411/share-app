package com.soft1851.share.content.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-30 10:15
 */
public class UserCenterFeignConfiguration {
    @Bean
    public Logger.Level level() {
        //让Feign打印所有请求细节
        return Logger.Level.FULL;
    }
}
