package com.sxw.blog.web;


import com.sxw.blog.pojo.Author;
import com.sxw.blog.pojo.User;
import com.sxw.blog.service.AuthorService;
import com.sxw.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @Autowired
    UserService userService;

    @GetMapping("/authors")
    public Author get(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return authorService.findOne(user.getId());
    }

    @PutMapping("/authors")
    public void update(HttpSession session, @RequestParam("description") String description) {
        User user = (User) session.getAttribute("user");
        authorService.save(description,user.getId());
    }

    @GetMapping("authors/info")
    public Author getAuthorInfo() {
        Author author = authorService.findOne(1);
        author.setUser(userService.getOne(1));
        return  author;
    }
}
