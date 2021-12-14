package com.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author ZouYangMing
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Component
public class Users {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private char sex;
    private String squad;
    private char teacher;
}
