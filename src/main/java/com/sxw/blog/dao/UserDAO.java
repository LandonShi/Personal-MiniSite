package com.sxw.blog.dao;

import com.sxw.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsernameAndPassword(String username, String password);
}
