package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public class BlogRepository {
     private final JdbcClient jdbcClient;

     public BlogRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
     }

     public List<Blog> findAll(){
        return jdbcClient.sql("SELECT id, title, contents FROM blogs")
        .query(Blog.class)
        .list();
     }

     public void save(BlogForm blog){
      jdbcClient.sql("INSERT INTO blogs (title, contents) VALUES (:title, :contents)")
      .param("title", blog.getTitle())
      .param("contents", blog.getContents())
      .update();
     }

     public Optional<Blog> findById(Long id){
      return jdbcClient.sql("SELECT id, title, contents FROM blogs WHERE id = :id")
         .param("id", id)
         .query(Blog.class)
         .optional();
     }

     public void deleteById(Long id){
      jdbcClient.sql("DELETE FROM blogs WHERE id = :id")
         .param("id", id)
         .update();
     }

     public List<Blog> searchByTitle(String keyword){
      return jdbcClient.sql("SELECT id, title, contents FROM blogs WHERE title LIKE :keyword")
         .param("keyword", "%" + keyword + "%")
         .query(Blog.class)
         .list();
     }
}
