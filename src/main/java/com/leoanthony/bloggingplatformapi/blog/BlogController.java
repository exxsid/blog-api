package com.leoanthony.bloggingplatformapi.blog;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

    @PutMapping("/posts/{id}")
    public ResponseEntity<Blog> updateBlog(
            @RequestBody BlogRequestBody blog,
            @PathVariable Long id
    ){
        LocalDateTime now = LocalDateTime.now();

        int res = blogRepository.updateBlog(
                id,
                blog.title(),
                blog.content(),
                blog.category(),
                blog.tags(),
                now
        );

        if (res > 0) {
            return new ResponseEntity<>(blogRepository.findById(id).get(), HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Blog> deleteBlog(
            @PathVariable Long id
    ) {
        try {
            // get the blog before deleting the record
            Blog blog = blogRepository.findById(id).get();
            if (blog == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            int res = blogRepository.deleteBlog(id);
            if (res < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
