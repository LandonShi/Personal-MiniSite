package com.sxw.blog.dao;

import com.sxw.blog.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogDAO extends JpaRepository<Blog, Integer> {

    @Modifying
    @Query(value = "update blog set firstPicture = ?1 where id = ?2", nativeQuery = true)
    void updateFirstPicture(String firstPicture, int bid);

    /*
    后台条件查询
     */
    @Query(value="select * from blog where blog.title like ?1 and type_id = ?2", nativeQuery = true)
    List<Blog> findByKeywordAndType(String keyword, int tid);

    /*
    查看次数最多的文章列表
     */
    @Query(value="select * from blog order by blog.views desc limit 0,10", nativeQuery = true)
    List<Blog> listBlogViews();

    /*
    前台条件查询
     */
    @Query(value="select * from blog where blog.title like ?1 or blog.content like ?1 order by blog.views desc", nativeQuery = true)
    List<Blog> findAllByKeyword(String keyword);

    /*
    前台相同分类文章
     */
    @Query(value="select * from blog where blog.type_id = ?1 order by blog.views desc limit 0,10", nativeQuery = true)
    List<Blog> listByType(int tid);

    /*
    分类查询页面
     */
    @Query(value="select * from blog where blog.type_id = ?1 order by blog.views desc", nativeQuery = true)
    List<Blog> listByTypeBlogs(int tid);

    /*
    更新文章查看次数views
     */
    @Modifying
    @Query(value = "update blog set blog.views = ?1 where id = ?2", nativeQuery = true)
    void blogViews(int views, int bid);

    /*
    查询最热十条文章
     */
    @Query(value="select * from blog order by blog.views desc limit 0,10", nativeQuery = true)
    List<Blog> listTen();

}
