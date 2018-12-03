package com.wangyuxuan.controller;

import com.wangyuxuan.pojo.T_user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/3 16:20
 * @Description:
 */
@Controller
@RequestMapping("/")
public class AdminController {

    @RequestMapping("/loginAdmin")
    public String login(T_user user, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            return "admin";
        }catch (Exception e){
            model.addAttribute("error", "用户名或密码错误");
            return "../../login";
        }
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/student")
    public String student(){
        return "admin";
    }

    @RequestMapping("/teacher")
    public String teacher(){
        return "admin";
    }
}
