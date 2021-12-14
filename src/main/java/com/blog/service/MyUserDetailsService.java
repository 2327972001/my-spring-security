package com.blog.service;

import com.blog.bean.Users;
import com.blog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDao.getUserName(username);
        if(userDao.getUserName(username) == null){//没有查到用户名，认证失败
            throw new UsernameNotFoundException("用户名不存在！");
        }

        //设置权限 实际上是从数据库取值
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,admin");
        return new User(users.getUsername(),new BCryptPasswordEncoder().encode(users.getPassword()),auth);
    }
}
