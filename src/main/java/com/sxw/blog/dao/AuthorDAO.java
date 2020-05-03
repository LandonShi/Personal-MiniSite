package com.sxw.blog.dao;

import com.sxw.blog.pojo.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuthorDAO extends JpaRepository<Author, Integer> {

    @Modifying
    @Query(value = "update author set author.description = ?1 where user_id = ?2",nativeQuery = true)
    void update(String description,int uid);
}
