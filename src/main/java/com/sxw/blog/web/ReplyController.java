package com.sxw.blog.web;

import com.sxw.blog.pojo.Blog;
import com.sxw.blog.pojo.Comment;
import com.sxw.blog.pojo.Reply;
import com.sxw.blog.service.CommentService;
import com.sxw.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ReplyController {

    @Autowired
    ReplyService replyService;
    @Autowired
    CommentService commentService;

    @PostMapping("/replies")
    public void save(@RequestParam("content") String content, @RequestParam("cid") int cid) {
        Comment comment = commentService.findOne(cid);
        Blog blog = comment.getBlog();
        Reply reply = new Reply();
        reply.setComment(comment);
        reply.setContent(content);
        reply.setBlog(blog);
        reply.setFlag(1);
        reply.setCreateTime(new Date());
        replyService.save(reply);
    }

    @GetMapping("/replies/{bid}")
    public int getReplyCount(@PathVariable("bid") int bid) {
        return replyService.replyCount(bid);
    }
}
