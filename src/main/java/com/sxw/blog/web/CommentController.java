package com.sxw.blog.web;

import com.sxw.blog.pojo.Blog;
import com.sxw.blog.pojo.Comment;
import com.sxw.blog.pojo.Reply;
import com.sxw.blog.service.BlogService;
import com.sxw.blog.service.CommentService;
import com.sxw.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Autowired
    ReplyService replyService;

    @PostMapping("/comments/{bid}")
    public void save(@PathVariable("bid") int bid, HttpServletRequest request) {
        Blog blog = blogService.findOne(bid);
        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setContent(request.getParameter("content"));
        comment.setNickname(request.getParameter("nickname"));
        comment.setEmail(request.getParameter("email"));
        comment.setCreateTime(new Date());
        comment.setFlag(0);
        commentService.save(comment);
    }

    /*
    获取评论列表
     */
    @GetMapping("/comments")
    public List<Comment> list(@RequestParam("bid") int bid) {
        List<Comment> comments = commentService.list(bid);
        for(Comment comment: comments) {
            List<Reply> replies = replyService.findListByBlogAndComment(bid, comment.getId());
            comment.setReplies(replies);
        }
        return comments;
    }

}
