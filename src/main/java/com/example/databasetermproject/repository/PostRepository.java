package com.example.databasetermproject.repository;

import com.example.databasetermproject.controller.SearchForm;
import com.example.databasetermproject.domain.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);
    List<Post> find(SearchForm searchForm);
}