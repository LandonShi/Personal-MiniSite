package com.sxw.blog.service;

import com.sxw.blog.dao.BlogDAO;
import com.sxw.blog.pojo.Blog;
import com.sxw.blog.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogDAO blogDAO;

    public void save(Blog blog) {
        blogDAO.save(blog);
    }

    @Transactional
    public void saveFistPicture(String firstPicture, int bid) {
        blogDAO.updateFirstPicture(firstPicture,bid);
    }

    /*
    查询全部博客
     */

    public Page4Navigator<Blog> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page<Blog> pageFromJPA = blogDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    /*
    删除博客
     */
    public void deleteBlog(int bid) {
        blogDAO.delete(bid);
    }

    /*
    获取博客
     */
    public Blog findOne(int bid) {
        return blogDAO.findOne(bid);
    }

    /*
    条件查询
     */
    public List<Blog> search(String keyword, int tid) {
        return blogDAO.findByKeywordAndType("%"+ keyword +"%", tid);
    }

    /*
    获取查看次数最多的文章列表
     */
    public List<Blog> listViews() {
        return blogDAO.listBlogViews();
    }

    /*
    条件查询
     */
    public List<Blog> listBlogByKeyword(String keyword) {
        return blogDAO.findAllByKeyword("%"+keyword+"%");
    }

    /*
    返回推荐相似的文章
     */
    public List<Blog> listByType(int tid) {
        return blogDAO.listByType(tid);
    }

    /*
    返回一个分类下所有文章
     */
    public List<Blog> listByTypeBlogs(int tid) {
        return blogDAO.listByTypeBlogs(tid);
    }

    /*
    查看次数+1
     */
    @Transactional
    public void updateViews(int views, int bid) {
        blogDAO.blogViews(views, bid);
    }

    /*
    全部文章
     */
    public List<Blog> listAllBlog() {
        return blogDAO.findAll();
    }

    /*
    获取最热十条文章
     */
    public List<Blog> listBlog() {
        return blogDAO.listTen();
    }
}
