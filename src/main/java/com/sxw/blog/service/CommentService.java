package com.sxw.blog.service;

import com.sxw.blog.dao.CommentDAO;
import com.sxw.blog.pojo.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;


    public void save(Comment comment) {
        commentDAO.save(comment);
    }

    public List<Comment> list(int bid) {
        return commentDAO.findCommentByBlog(bid);
    }

    public Comment findOne(int cid) {
        return commentDAO.findOne(cid);
    }

    public List<Comment> listSort(int bid, String sort) {
        if(sort.equals("desc"))
            return commentDAO.findSortCommentDESC(bid);
        else
            return commentDAO.findSortCommentASC(bid);
    }

}
