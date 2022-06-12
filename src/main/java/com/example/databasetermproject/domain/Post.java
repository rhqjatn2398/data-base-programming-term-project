package com.example.databasetermproject.domain;

import com.example.databasetermproject.controller.PostForm;

public class Post {
    private int id;
    private long uid;
    private String title;
    private String content;
    private String area;
    private String phone;
    private String writer;
    private String category;

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Post() {}

    public Post(PostForm postForm, int uid)  {
        this.uid = uid;
        title = postForm.getTitle();
        content = postForm.getContent();
        area = postForm.getArea();
        phone = postForm.getPhone();
        category = postForm.getCategory(); // 이것도 실제로는 받아 와야 함
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
