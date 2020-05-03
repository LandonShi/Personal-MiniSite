package com.sxw.blog.service;

import com.sxw.blog.dao.AuthorDAO;
import com.sxw.blog.pojo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthorService {

    @Autowired
    AuthorDAO authorDAO;

    public Author findOne(int uid) {
        return authorDAO.findOne(uid);
    }

    @Transactional
    public void save(String description,int uid) {
        authorDAO.update(description, uid);
    }
}
