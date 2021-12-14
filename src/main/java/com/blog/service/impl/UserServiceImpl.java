package com.blog.service.impl;

import com.blog.bean.Users;
import com.blog.dao.UserDao;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<Users> getUserList() {
        return userDao.getUserList();
    }
}
