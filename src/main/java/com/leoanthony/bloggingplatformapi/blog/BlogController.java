package com.leoanthony.bloggingplatformapi.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class BlogController {

    private BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("")
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }
}
