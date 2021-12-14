package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
* @program SecurityConfig
* @author Mr.Zou
* @create 2021-12-13 21:50
*/
//@EnableWebSecurity
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource DataSource;

    /**
    * @program SecurityConfig
    * @description 授权
    * @author Mr.Zou
    * @create 2021-12-13 21:51
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人都能访问，功能页面只有对应权限的人才能访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/edit/**").hasRole("op");

        //没有权限访问，会自动跳转到登录页
        http.formLogin();
    }

    /**
    * @program SecurityConfig
    * @description 认证
    * @author Mr.Zou
    * @create 2021-12-14 17:54
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //auth.inMemoryAuthentication().withUser("2327972001").password(passwordEncoder.encode("123456")).roles("user");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
