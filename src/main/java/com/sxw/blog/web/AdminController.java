package com.sxw.blog.web;

import com.sxw.blog.pojo.User;
import com.sxw.blog.service.UserService;
import com.sxw.blog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public Object login(@RequestBody User user, HttpSession session) {
        User u = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(u != null) {
            session.setAttribute("user", u);
            return Result.success();
        }
        else {
            return Result.fail("账户名或者密码错误");
        }
    }
}
