package com.sxw.blog.web;


import com.sxw.blog.pojo.Type;
import com.sxw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TypeController {

    @Autowired
    TypeService typeService;

    /*
    增加分类专题
     */
    @PostMapping("/types")
    public void save(@RequestParam("name") String name) {
        Type type = new Type();
        type.setName(name);
        typeService.save(type);
    }

    /*
    获取分类专题列表
     */
    @GetMapping("/types")
    public List<Type> listType() {
        return typeService.listType();
    }

    /*
    获取分类专题
     */
    @GetMapping("/types/{tid}")
    public Type findOne(@PathVariable("tid") int tid) {
        return typeService.findOne(tid);
    }

    /*
    修改分类
     */
    @PutMapping("/types/{tid}")
    public void updateType(@PathVariable("tid") int tid, @RequestParam("name") String name) {
        Type type = typeService.findOne(tid);
        type.setName(name);
        System.out.println(type.toString());
        typeService.update(type);
    }

    /*
    删除分类
     */
    @DeleteMapping("/types/{tid}")
    public String delete(@PathVariable("tid") int tid) {
        typeService.delete(tid);
        return null;
    }

}
