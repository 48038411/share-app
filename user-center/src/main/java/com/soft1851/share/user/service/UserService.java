package com.soft1851.share.user.service;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.domain.dto.*;
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
    ResponseDTO findById(Integer id);

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

    /**
     * 扣积分
     * @param userAddBonusMsgDTO
     * @return
     */
    User reduceBonus(UserAddBonusMsgDTO userAddBonusMsgDTO);

    /**
     * 用户签到
     * @param signInDTO
     * @return
     */
    ResponseDTO signIn(UserSignInDTO signInDTO);

    /**
     * 判断用户是否签到的
     * @param signInDTO
     * @return
     */
    ResponseDTO checkIsSign(UserSignInDTO signInDTO);

    /**
     * 查询用户日志
     * @param userDTO
     * @return
     */
    ResponseDTO getLog(UserDTO userDTO);
}
