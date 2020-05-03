package com.sxw.blog.web;

import com.sxw.blog.pojo.Blog;
import com.sxw.blog.pojo.User;
import com.sxw.blog.service.BlogService;
import com.sxw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;

    @GetMapping("/users/{bid}")
    public User getUser(@PathVariable("bid") int bid) {
        Blog blog = blogService.findOne(bid);
        return blog.getUser();
    }
}
