package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    // public List<Blog> findAll(){
    //     return blogRepository.findAll();
    // }

    public void add(BlogForm form){
        if (form.getTitle() == null || form.getTitle().isBlank()) {
           throw new IllegalArgumentException("タイトルを入力してください");
  }
        blogRepository.save(new BlogForm(form.getTitle(),form.getContents()));
    }

    public Optional<Blog> findById(Long id){
        return blogRepository.findById(id);
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    public List<Blog> search(String keyword){
        if(keyword == null || keyword.isBlank()){
            return blogRepository.findAll();
        }
        return blogRepository.searchByTitle(keyword);
    }
}
