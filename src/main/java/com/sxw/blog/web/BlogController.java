package com.sxw.blog.web;


import com.sxw.blog.pojo.Blog;
import com.sxw.blog.pojo.Type;
import com.sxw.blog.pojo.User;
import com.sxw.blog.service.BlogService;
import com.sxw.blog.service.TypeService;
import com.sxw.blog.util.ImageUtil;
import com.sxw.blog.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    /*
    增加博客
     */
    @PostMapping("blogs")
    public void save(MultipartFile image, HttpServletRequest request, @RequestParam("obj") String obj) throws IOException {
        Blog blog = new Blog();
        Type type = typeService.findOne(Integer.parseInt(request.getParameter("tid")));
        blog.setType(type);  //隶属分类
        blog.setTitle(request.getParameter("title"));  //标题
        blog.setContent(request.getParameter("content"));  //文章内容
        blog.setFlag(request.getParameter("flag"));   //状态
        blog.setCreateDate(new Date());  //创建日期
        blog.setUpdateDate(new Date());  //更新日期
        blog.setViews(0);  //查看数
        blog.setNums(request.getParameter("content").length());
        User user = (User) request.getSession().getAttribute("user");
        blog.setUser(user);  //当前隶属用户
        if(obj.equals("published")) {
            blog.setPublish(true);  //发布？
        } else {
            blog.setPublish(false);  //发布？
        }
        blogService.save(blog);
        //更新地址
        String firstPicture = saveOrUpdateImageFile(blog,image,request);
        blogService.saveFistPicture(firstPicture,blog.getId());
    }
    public String saveOrUpdateImageFile(Blog bean, MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/picture"));
        File file = new File(imageFolder,bean.getId() + ".jpg");
        String firstPicture = file.toString();
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return firstPicture;
    }

    /*
    分类管理页 查询全部博客，并进行分页
     */
    @GetMapping("blogs")
    public Page4Navigator<Blog> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "10") int size) {
        start = start<0?0:start;
        return blogService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
    }

    /*
    博客删除
     */
    @DeleteMapping("blogs/{bid}")
    public String delete(@PathVariable("bid") int bid) {
        blogService.deleteBlog(bid);
        return null;
    }

    /*
    编辑博客
     */
    @GetMapping("blogs/{bid}")
    public Blog updateBlog(@PathVariable("bid") int bid) {
        return blogService.findOne(bid);
    }

    @PutMapping("blogs/{bid}")
    public void updateViews(@PathVariable("bid") int bid) {
        int views = blogService.findOne(bid).getViews();
        int new_views = ++views;
        blogService.updateViews(new_views, bid);
    }

}
