package com.soft1851.share.user.service.impl;

import com.soft1851.share.user.common.ResponseResult;
import com.soft1851.share.user.dao.UserMapper;
import com.soft1851.share.user.domain.dto.UserDTO;
import com.soft1851.share.user.domain.entity.User;
import com.soft1851.share.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-09-25 14:41
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Override
    public ResponseResult login(UserDTO userDTO) {
//        User user = userMapper.findByAccount(userDTO.getAccount());
//        if (user != null){
//            if (userDTO.getPassword().equals(user.getPassword())){
//                return new ResponseResult(0,"登录成功",user);
//            }else {
//                return new ResponseResult(-1,"密码错误","密码错误");
//            }
//        }else {
//            return new ResponseResult(1001,"用户不存在","用户名不存在");
//        }
        return new ResponseResult(1001,"用户不存在","用户名不存在");
    }

    @Override
    public ResponseResult getUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return new ResponseResult(200,"查询成功",user);
    }

        @Override
        public User findById(Integer id) {
            return this.userMapper.selectByPrimaryKey(id);

    }
}
