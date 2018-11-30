package com.wangyuxuan.controller;

import com.wangyuxuan.pojo.User;
import com.wangyuxuan.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/11/30 14:37
 * @Description:
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }

    @RequestMapping("/showUser/{id}")
    public String showUser(@PathVariable int id, HttpServletRequest request){
        User user = userService.getUserById(id);
        request.setAttribute("user", user);
        return "showUser";
    }
}
