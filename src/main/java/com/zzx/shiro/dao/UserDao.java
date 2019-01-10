package com.zzx.shiro.dao;


import com.zzx.shiro.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where user_name=#{userName} and password=#{password}")
    UserEntity getUser(@Param("userName") String userName,
                @Param("password") String password);
    @Select("select * from user where user_name=#{userName}")
    UserEntity getone(@Param("userName") String userName);
}
