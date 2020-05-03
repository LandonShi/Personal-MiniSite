package com.sxw.blog.service;

import com.sxw.blog.dao.ReplyDAO;
import com.sxw.blog.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReplyService {

    @Autowired
    ReplyDAO replyDAO;

    public void save(Reply reply) {
        replyDAO.save(reply);
    }

    public List<Reply> findListByBlogAndComment(int bid,int cid) {
        return replyDAO.listReply(bid, cid);
    }

    public int replyCount(int bid) {
        return replyDAO.countReply(bid);
    }
}
