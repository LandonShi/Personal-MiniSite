package com.sxw.blog.dao;

import com.sxw.blog.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyDAO extends JpaRepository<Reply, Integer> {

    /*
    查询当前评论下所有回复
     */
    @Query(value="select * from reply where blog_id = ?1 and comment_id = ?2", nativeQuery = true)
    List<Reply> listReply(int bid, int cid);

    /*
    获取评论集合个数
     */
    @Query(value="select count(*) from reply where blog_id = ?1", nativeQuery = true)
    int countReply(int bid);
}
