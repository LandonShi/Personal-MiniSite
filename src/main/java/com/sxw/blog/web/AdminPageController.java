package com.sxw.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminPageController {

    @GetMapping(value = "admin_login")
    public String adminLogin() {
        return "admin/login";
    }

    @GetMapping(value = "admin_logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:admin_login";
    }

    @GetMapping(value = "admin_index")
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping(value = "admin_blog_list")
    public String blogList() {
        return "admin/blogList";
    }

    @GetMapping(value = "admin_blog_input")
    public String blogInput() {
        return "admin/blogInput";
    }

    @GetMapping(value = "admin_type_list")
    public String typeList() {
        return "admin/typeList";
    }

    @GetMapping(value = "admin_type_input")
    public String typeInput() {
        return "admin/typeInput";
    }

    @GetMapping(value = "admin_type_edit")
    public String typeEdit() {
        return "admin/typeEdit";
    }

    @GetMapping(value = "admin_atlas_list")
    public String photoManager() {
        return "admin/atlasList";
    }

    @GetMapping(value = "admin_atlas_input")
    public String photoInput() {
        return "admin/atlasInput";
    }

    @GetMapping(value = "admin_atlas_edit")
    public String atlasEdit() {
        return "admin/atlasEdit";
    }

    @GetMapping(value = "admin_picture_list")
    public String pictureList() {
        return "admin/pictureList";
    }

    @GetMapping(value = "admin_author_info")
    public String getAuthorPage() {
        return "admin/authorListInfo";
    }

}
