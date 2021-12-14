package com.blog.controller;

import com.blog.bean.Users;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Index controller.
 *
 * @author ZouYangMing
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     * Index string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping({"","/","/index"})
    public String index(Model model){
        model.addAttribute("msg","<h1>你好,SpringBoot</h1>");
        model.addAttribute("list", Arrays.asList("你好","不好","测试"));
        return "index";
    }

    /**
     * Admin string.
     *
     * @return the string
     */
    @RequestMapping("/admin")
    public String admin(){
        return "admin/index";
    }

    /**
     * Edit string.
     *
     * @return the string
     */
    @RequestMapping("/edit")
    public String edit(){
        return "edit/index";
    }

    /**
     * User string.
     *
     * @return the string
     */
    @RequestMapping("/user")
    public String user(){
        return "user/index";
    }

    /**
     * Userlist list.
     *
     * @return the list
     */
    @ResponseBody
    @RequestMapping("/userList")
    public List<Users> userlist(){
        return userService.getUserList();
    }

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping("/403")
    public String error403(){
        return "error/403";
    }

    @ResponseBody
    @RequestMapping("/update")
    //@Secured({"ROLE_user","ROLE_admin"})//只有具有ROLE_user、ROLE_admin权限的用户才能访问此方法
    @PreAuthorize("hasAnyAuthority('admin','user')")//admin、user权限的用户才能访问此方法
    public String update(){
        return "更新操作";
    }

    @ResponseBody
    @RequestMapping("/all")
    @PostAuthorize("hasAnyAuthority('admin')")
    @PostFilter("filterObject.username == 'ceshi'")//对返回数据进行过滤，只返回匹配的数据
    public List<Users> getAllUser() {
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(11,"测试1","2327972001","123456",'1',"",'1'));
        list.add(new Users(11,"测试2","admin","123456",'1',"",'1'));
        list.add(new Users(11,"测试3","ceshi","123456",'1',"",'1'));
        list.add(new Users(11,"测试4","tset","123456",'1',"",'1'));
        System.out.println(list);
        return list;
    }
}
