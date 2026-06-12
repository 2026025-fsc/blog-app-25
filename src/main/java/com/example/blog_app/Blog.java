package com.example.blog_app;

public class Blog {
    private final Long id;
    private final String title;
    private final String contents;

    public Blog(Long id, String title, String contents){
        this.id=id;
        this.title = title;
        this.contents = contents;   
    }

    public Long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    

    


}
