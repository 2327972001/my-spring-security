package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
* @program SecurityConfig
* @author Mr.Zou
* @create 2021-12-13 21:50
*/
//@EnableWebSecurity
@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
    * @program SecurityConfig
    * @description 认证
    * @author Mr.Zou
    * @create 2021-12-14 17:54
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    /**
    * @program SecurityConfigTest
    * @description 授权
    * @author Mr.Zou
    * @create 2021-12-14 19:14
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //logoutUrl()退出地址 logoutSuccessUrl()退出成功后跳转的页面
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/index").permitAll();

        //没有权限跳转的页面
        //http.exceptionHandling().accessDeniedPage("/login");
        http.formLogin() //自定义登陆页面
                .loginPage("/login")//登录页访问地址
                .loginProcessingUrl("/loginPost")//登录提交的地址
                //.successForwardUrl("/index")//登录成功跳转的地址
                .defaultSuccessUrl("/index")//登录成功跳转的地址
                .permitAll()
                .and().authorizeRequests()
                    .antMatchers("/","/index").permitAll()//设置那些路径不用登录可以访问
                    .antMatchers("/admin/**").hasAuthority("admin")//用户具有admin权限才能访问
                    .antMatchers("/user/**").hasAnyAuthority("user","admin")//用户具有user、admin权限才能访问
                    .antMatchers("/edit/**").hasAnyAuthority("op","admin")//用户具有op、admin权限才能访问
                    .anyRequest().authenticated()//其他地址的访问均需验证权限
                .and()
                    //关闭csrf防护
                    .csrf().disable();

        //记住我功能 默认有效期14天 设定rememberMe()的name为remember
        http.rememberMe().rememberMeParameter("remember");
    }
}
