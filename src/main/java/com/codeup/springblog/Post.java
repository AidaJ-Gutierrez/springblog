package com.codeup.springblog;

public class Post {
    private String title;
    private String body;

    public Post() {
        this.title = title;
        this.body = body;
    }

    public Post(String new_post, String new_body) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

