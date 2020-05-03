package com.sxw.blog.web;


import com.sxw.blog.pojo.*;
import com.sxw.blog.service.*;
import com.sxw.blog.util.Page4Navigator;
import com.sxw.blog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
public class ForeRESTController {


    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    AtlasService atlasService;
    @Autowired
    PictureService pictureService;
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;

    @GetMapping("/search")
    public List<Blog> list(@RequestParam("keyword") String keyword, @RequestParam("tid") int tid) {
        return blogService.search(keyword,tid);
    }

    /*
    获取分类进行分页
     */
    @GetMapping("/typesPage")
    public Page4Navigator<Type> listType(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        start = start<0?0:start;
        return typeService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
    }

    /*
    获取图集进行分页
     */
    @GetMapping("/atlasesPage")
    public Page4Navigator<Atlas> listAtlas(@RequestParam(value = "start", defaultValue = "0") int start,
                                      @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start<0?0:start;
        return atlasService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
    }

    /*
    获取查看次数最多的文章
     */
    @GetMapping("/viewing")
    public List<Blog> listViewsBlog() {
        return blogService.listViews();
    }

    /*
    搜索文章
     */
    @GetMapping("/searchBlog")
    public List<Blog> listBlogsByKeyword(@RequestParam("keyword") String keyword) {
        return blogService.listBlogByKeyword(keyword);
    }

    /*
    文章详情页 获取文章详情
     */
    @GetMapping("/blogsDetail/{bid}")
    public Blog findOne(@PathVariable("bid") int bid) {
        return blogService.findOne(bid);
    }

    /*
    获取相似推荐列表
     */
    @GetMapping("/blogsLike/{bid}")
    public List<Blog> listLikeBlog(@PathVariable("bid") int bid) {
        Blog blog = blogService.findOne(bid);
        Type type = blog.getType();
        return blogService.listByType(type.getId());
    }

    /*
    条件排序
     */
    @GetMapping("/commentsSort")
    public List<Comment> listSort(@RequestParam("bid") int bid, @RequestParam("sort") String sort) {
        List<Comment> comments = commentService.listSort(bid, sort);
        for(Comment comment: comments) {
            List<Reply> replies = replyService.findListByBlogAndComment(bid, comment.getId());
            comment.setReplies(replies);
        }
        return comments;
    }

    /*
    检查是否登录
     */
    @GetMapping("/check")
    public Object checkLogin(HttpSession session) {
        if(session == null) {
            return Result.fail("未登录");
        } else {
            return Result.success();
        }
    }

    /*
    获取专题分类列表与列表下的文章信息
     */
    @GetMapping("/foreTypes")
    public List<Type> listType() {
        List<Type> types = typeService.listType();
        for(Type type: types) {
            List<Blog> blogs = blogService.listByTypeBlogs(type.getId());
            type.setBlogs(blogs);
        }

        return types;
    }

    /*
    一个分类下的文章信息
     */
    @GetMapping("/typeBlogs/{tid}")
    public List<Blog> listTypeBlogs(@PathVariable("tid") int tid) {
        return blogService.listByType(tid);
    }

    /*
    归档页 获取全部文章
     */
    @GetMapping("/blogsAll")
    public List<Blog> listAllBlogs() {
        return blogService.listAllBlog();
    }

    /*
    获取图集下的图片
     */
    @GetMapping("/atlasesPicture/{aid}")
    public Atlas pictures(@PathVariable("aid") int aid) {
        Atlas atlas = atlasService.findOne(aid);
        List<Picture> pictures = pictureService.list(aid);
        atlas.setPictures(pictures);
        return atlas;
    }

    /*
    获取查看数最多的十条文章
     */
    @GetMapping("/blogsTen")
    public List<Blog> listHotBlog() {
        return blogService.listBlog();
    }

    /*
    游客 评论
     */
    @PostMapping("/commentWeb")
    public void addComet(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String content = request.getParameter("content");
        Blog blog = blogService.findOne(50);   //50这个id 专门用来记录游客留言的id
        Comment comment = new Comment();
        comment.setNickname(nickname);
        comment.setEmail(email);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setBlog(blog);
        comment.setFlag(0);
        commentService.save(comment);
    }
}
