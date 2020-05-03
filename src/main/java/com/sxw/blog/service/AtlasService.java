package com.sxw.blog.service;

import com.sxw.blog.dao.AtlasDAO;
import com.sxw.blog.pojo.Atlas;
import com.sxw.blog.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtlasService {

    @Autowired
    AtlasDAO atlasDAO;

    public Page4Navigator<Atlas> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page<Atlas> pageFromJPA = atlasDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void save(Atlas atlas) {
        atlasDAO.save(atlas);
    }

    public Atlas findOne(int aid) {
        return atlasDAO.findOne(aid);
    }

    public void update(Atlas atlas) {
        atlasDAO.save(atlas);
    }

    public void delete(int aid) {
        atlasDAO.delete(aid);
    }

    public Atlas getRecentlyAtlas() {
        return atlasDAO.listRecentlyAtlas();
    }

    public List<Atlas> listAllOrderByCreateTime() {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return atlasDAO.findAll(sort);
    }
}
