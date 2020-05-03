package com.sxw.blog.service;

import com.sxw.blog.dao.UserDAO;
import com.sxw.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User findByUsernameAndPassword(String username, String password) {
            return userDAO.findByUsernameAndPassword(username,password);
    }

    public User getOne(int uid) {
        return userDAO.getOne(uid);
    }
}
