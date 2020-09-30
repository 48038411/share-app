package com.soft1851.share.user.service;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.domain.dto.UserDTO;
import com.soft1851.share.user.domain.entity.User;

import java.util.List;

/**
 * @author Guorc
 */
public interface UserService {
    ResponseResult login(UserDTO userDTO);

    ResponseResult getUserInfo(Integer id);
    /**
     * 根据id获得用户详情
     * @param id
     * @return User
     */
    User findById(Integer id);
}
