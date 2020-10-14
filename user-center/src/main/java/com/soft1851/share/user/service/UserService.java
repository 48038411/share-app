package com.soft1851.share.user.service;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.domain.dto.LoginDTO;
import com.soft1851.share.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.share.user.domain.dto.UserDTO;
import com.soft1851.share.user.domain.entity.User;

import java.util.List;

/**
 * @author Guorc
 */
public interface UserService {
    ResponseResult getUserInfo(Integer id);

    /**
     * 根据id获得用户详情
     *
     * @param id
     * @return User
     */
    User findById(Integer id);

    /**
     * 用户修改积分接口
     *
     * @param addBonusMsgDTO
     * @return
     */
    User updateBonus(UserAddBonusMsgDTO addBonusMsgDTO);

    /**
     * 登陆接口
     * @param loginDTO
     * @return
     */
    User login(LoginDTO loginDTO,String openId);
}
