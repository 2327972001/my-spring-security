package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//开启注解
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class MySpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringSecurityApplication.class, args);
        System.out.println("http://127.0.0.1:8080");
    }

}
