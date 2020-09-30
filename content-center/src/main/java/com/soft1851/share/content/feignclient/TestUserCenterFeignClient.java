package com.soft1851.share.content.feignclient;

import com.soft1851.share.content.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Guorc
 */
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {
    @GetMapping("/users/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);
}
