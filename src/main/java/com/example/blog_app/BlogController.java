package com.example.blog_app;


import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    //  @GetMapping("/blogs")
    // public String blogs(Model model) {
    //     model.addAttribute("blogs", blogService.findAll());
    //     return "/blogs";
    // }

    @GetMapping("/blogs/new")
    public String newBlogs() {
        return "/blogs/new";
    }

    @PostMapping("/new")
    public String newForm(BlogForm form) {
        blogService.add(form);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blogs/detail";
    }
    
    @PostMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("blogs", blogService.search(keyword));
        return "blogs";
    }
    
    
    
    
}
