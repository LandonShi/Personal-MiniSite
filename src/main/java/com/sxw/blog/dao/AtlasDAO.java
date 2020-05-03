package com.sxw.blog.dao;


import com.sxw.blog.pojo.Atlas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AtlasDAO extends JpaRepository<Atlas, Integer> {

    /*
    查询最新上传的图集
     */
    @Query(value="select * from atlas order by atlas.createDate desc limit 0,1", nativeQuery = true)
    Atlas listRecentlyAtlas();

}
