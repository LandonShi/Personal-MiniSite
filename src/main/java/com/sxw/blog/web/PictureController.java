package com.sxw.blog.web;

import com.sxw.blog.pojo.Atlas;
import com.sxw.blog.pojo.Picture;
import com.sxw.blog.pojo.User;
import com.sxw.blog.service.AtlasService;
import com.sxw.blog.service.PictureService;
import com.sxw.blog.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class PictureController {

    @Autowired
    PictureService pictureService;
    @Autowired
    AtlasService atlasService;

    @GetMapping("/pictures/{aid}")
    public List<Picture> list(@PathVariable("aid") int aid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return pictureService.list(user.getId(),aid);
    }

    /*
    增加图片
     */
    @PostMapping("/pictures")
    public Object add(@RequestParam("aid") int aid, @RequestParam("title") String title,
                      HttpSession session, HttpServletRequest request, MultipartFile image) {
        Picture picture = new Picture();
        Atlas atlas = atlasService.findOne(aid);
        User user = (User) session.getAttribute("user");
        picture.setAtlas(atlas);
        picture.setUser(user);
        picture.setCreateDate(new Date());
        picture.setTitle(title);
        pictureService.add(picture);

        String folder = "img/pictureDetail";
        File imageFolder= new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,picture.getId()+".jpg");
        String fileName = file.getName();
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageFolder_middle = request.getServletContext().getRealPath("img/pictureThumbnail");
        File f_middle = new File(imageFolder_middle, fileName);
        f_middle.getParentFile().mkdirs();
        ImageUtil.resizeImage(file, 56, 56, f_middle);
        return picture;
    }

    /*
    删除图片
     */
    @DeleteMapping("/pictures/{pid}")
    public String delete(@PathVariable("pid") int pid, HttpServletRequest request) {
        Picture picture = pictureService.findOne(pid);
        pictureService.delete(picture);
        //删除详情图片
        String folder = "img/pictureDetail";
        File  imageFolder= new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,picture.getId()+".jpg");
        String fileName = file.getName();
        file.delete();

        //删除缩略图
        String imageFolder_middle= request.getServletContext().getRealPath("img/pictureThumbnail");
        File f_middle = new File(imageFolder_middle, fileName);
        f_middle.delete();
        return null;
    }

}
