package com.leoanthony.bloggingplatformapi.blog;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Blog b " +
            "SET b.title = :title, " +
            "b.content = :content, " +
            "b.category = :category, " +
            "b.tags = :tags, " +
            "b.updatedAt = :updatedAt " +
            "WHERE b.id = :id")
    int updateBlog(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("content") String content,
            @Param("category") String category,
            @Param("tags") List<String> tags,
            @Param("updatedAt")LocalDateTime updatedAt
            );
    @Transactional
    @Modifying
    @Query("DELETE FROM Blog b WHERE b.id = :id")
    int deleteBlog(@Param("id") Long id);
}
