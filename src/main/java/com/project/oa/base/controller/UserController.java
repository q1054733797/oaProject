package com.project.oa.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @ClassName: UserController
 * @Author: zhanghongkai
 * @Date: Create in 2019/3/5 11:41
 * @Version: 1.0
 */
@Controller
public class UserController {
    @RequestMapping("login")
    @ResponseBody
    public String login(String username, String password){
        System.out.println(username);
        System.out.println(password);
        String message = "";
        if("1054733797@qq.com".equals(username) && "123456".equals(password)){
            message = "ok";
        }else{
            message = "帐号或密码错误";
        }
        return message;
    }
}
