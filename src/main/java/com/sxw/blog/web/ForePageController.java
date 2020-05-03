package com.sxw.blog.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForePageController {

    @GetMapping(value = "fore_blog_list")
    public String questionList() {
        return "fore/blog";
    }

    @GetMapping(value = "fore_blog_type")
    public String questionType() {
        return "fore/blogType";
    }

    @GetMapping(value = "fore_blog_author")
    public String authorPage() {
        return "fore/authorPage";
    }

    @GetMapping(value = "fore_blog_detail")
    public String questionDetail() {
        return "fore/blogDetail";
    }

    @GetMapping(value = "fore_question_travel")
    public String questionTravel() {
        return "fore/questionTravel";
    }

    @GetMapping(value = "fore_travel_photo")
    public String travelPhoto() {
        return "fore/travelPhoto";
    }

    @GetMapping(value = "fore_time_axis")
    public String timeAxis() {
        return "fore/timeAxis";
    }

    @GetMapping("fore_blog_search")
    public String searchBlog() {
        return "fore/blogSearchResult";
    }

}


