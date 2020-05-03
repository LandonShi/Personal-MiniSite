package com.sxw.blog.web;

import com.sxw.blog.pojo.Atlas;
import com.sxw.blog.pojo.Picture;
import com.sxw.blog.service.AtlasService;
import com.sxw.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AtlasController {

    @Autowired
    AtlasService atlasService;
    @Autowired
    PictureService pictureService;

    /*
    增加图集
     */
    @PostMapping("/atlases")
    public void add(@RequestParam("name") String name) {
        Atlas atlas = new Atlas();
        atlas.setName(name);
        atlas.setCreateDate(new Date());
        atlasService.save(atlas);
    }

    /*
    获取图集
     */
    @GetMapping("/atlases/{aid}")
    public Atlas findOne(@PathVariable("aid") int aid) {
        return atlasService.findOne(aid);
    }

    /*
    修改图集
     */
    @PutMapping("/atlases/{tid}")
    public void updateType(@PathVariable("tid") int tid, @RequestParam("name") String name) {
        Atlas atlas = atlasService.findOne(tid);
        atlas.setName(name);
        atlasService.update(atlas);
    }

    /*
    删除图集
     */
    @DeleteMapping("/atlases/{aid}")
    public String delete(@PathVariable("aid") int aid) {
        atlasService.delete(aid);
        return null;
    }

    /*
    获取最新上传的图集
     */
    @GetMapping("/atlases")
    public Atlas getRecentlyAtlas() {
        Atlas atlas = atlasService.getRecentlyAtlas();
        int aid = atlas.getId();
        List<Picture> pictures = pictureService.list(aid);
        atlas.setPictures(pictures);
        return atlas;
    }

    /*
    获取全部图集
     */
    @GetMapping("/atlases/all")
    public List<Atlas> listAll() {
        List<Atlas> atlases = atlasService.listAllOrderByCreateTime();
        for(Atlas atlas: atlases) {
            List<Picture> pictures = atlas.getPictures();
            atlas.setPictures(pictures);
            atlas.setFirstPictureID(pictures.get(0).getId());
        }
        return atlases;
    }

}
