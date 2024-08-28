package com.leoanthony.bloggingplatformapi.blog;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BlogController {

    private BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/posts")
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @PostMapping("/posts")
    public Blog createBlog(
            @RequestBody BlogRequestBody blog
    ) {
        LocalDateTime now = LocalDateTime.now();
        Blog newBlog = new Blog();
        newBlog.setTitle(blog.title());
        newBlog.setContent(blog.content());
        newBlog.setCategory(blog.category());
        newBlog.setTags(blog.tags());
        newBlog.setCreatedAt(now);
        newBlog.setUpdatedAt(now);

        return blogRepository.save(newBlog);
    }
}
