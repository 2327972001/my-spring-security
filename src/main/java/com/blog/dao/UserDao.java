package com.blog.dao;

import com.blog.bean.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<Users> getUserList();
    Users getUserName(@Param("username") String username);
}
