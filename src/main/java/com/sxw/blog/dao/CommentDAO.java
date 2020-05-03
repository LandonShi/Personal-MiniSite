package com.sxw.blog.dao;

import com.sxw.blog.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    @Query(value="select * from comment where comment.blog_id = ?1" ,nativeQuery = true)
    List<Comment> findCommentByBlog(int bid);

    /*
    倒叙查询
     */
    @Query(value="select * from comment where comment.blog_id = ?1 order by comment.createTime desc" ,nativeQuery = true)
    List<Comment> findSortCommentDESC(int bid);

    /*
    正序查询
     */
    @Query(value="select * from comment where comment.blog_id = ?1 order by comment.createTime asc" ,nativeQuery = true)
    List<Comment> findSortCommentASC(int bid);
}
