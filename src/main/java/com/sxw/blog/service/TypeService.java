package com.sxw.blog.service;

import com.sxw.blog.dao.TypeDAO;
import com.sxw.blog.pojo.Type;
import com.sxw.blog.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeDAO typeDAO;

    public void save(Type type) {
        typeDAO.save(type);
    }

    public List<Type> listType() {
        return typeDAO.findAll();
    }

    /*
    查询分类专题
     */
    public Type findOne(int tid) {
        return typeDAO.getOne(tid);
    }

    /*
    查询全部分类 并分页
     */
    public Page4Navigator<Type> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page<Type> pageFromJPA = typeDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void update(Type type) {
        typeDAO.save(type);
    }

    /*
    删除
     */
    public void delete(int tid) {
        typeDAO.delete(tid);
    }
}
