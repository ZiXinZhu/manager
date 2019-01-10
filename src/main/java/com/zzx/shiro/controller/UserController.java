package com.zzx.shiro.controller;

import com.zzx.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Mr.John on 2019/1/7 21:34.
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/loginUser")
    private String login(String userName, String password, HttpSession session){
       return userService.login(userName, password,session);
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "login";
    }
}
