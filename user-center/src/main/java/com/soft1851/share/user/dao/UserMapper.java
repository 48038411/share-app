package com.soft1851.share.user.dao;


import com.soft1851.share.user.domain.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Guorc
 */
public interface UserMapper extends Mapper<User> {
    @Select("SELECT * FROM user WHERE account = #{account}")
    User findByAccount(String account);
}
