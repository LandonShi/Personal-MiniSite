package com.sxw.blog.dao;

import com.sxw.blog.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureDAO extends JpaRepository<Picture, Integer> {

    @Query(value="select * from picture where user_id = ?1 and atlas_id = ?2", nativeQuery = true)
    List<Picture> findAllByUserAndAtlas(int uid, int aid);

    @Query(value="select * from picture where picture.atlas_id = ?1", nativeQuery = true)
    List<Picture> findAllByUserAndAtlas(int aid);
}
