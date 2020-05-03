package com.sxw.blog.service;

import com.sxw.blog.dao.PictureDAO;
import com.sxw.blog.pojo.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {

    @Autowired
    PictureDAO pictureDAO;

    public List<Picture> list(int uid, int aid) {
        return pictureDAO.findAllByUserAndAtlas(uid, aid);
    }

    public void add(Picture picture) {
        pictureDAO.save(picture);
    }

    public Picture findOne(int pid) {
        return pictureDAO.findOne(pid);
    }

    public void delete(Picture picture) {
        pictureDAO.delete(picture);
    }

    public List<Picture> list(int aid) {
        return pictureDAO.findAllByUserAndAtlas(aid);
    }

}
