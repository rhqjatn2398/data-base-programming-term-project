package com.example.databasetermproject.controller;

import com.example.databasetermproject.domain.Post;
import com.example.databasetermproject.domain.member.Member;
import com.example.databasetermproject.domain.member.service.MemberService;
import com.example.databasetermproject.service.PostService;
import com.example.databasetermproject.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    public PostController(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @PostMapping(value = "/posts/new")
    public String submitNewPost(PostForm postForm, Model model, @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member loginMember)  {

        Post post = new Post(postForm, 100);
        post.setUid(loginMember.getId());

        postService.submitNewPost(post);
        return "/main/db"; // 메인 화면으로 이동
    }

    @GetMapping(value = "/posts/new")
    public String createForm(@RequestParam String category, Model model) {
        model.addAttribute("data", category);
        return "posts/post_form";
    }

    @GetMapping(value = "/posts/search")
    public String searchForm() {
        return "posts/post_search_form";
    }

    @PostMapping(value = "/posts/search")
    public String search(SearchForm searchForm, Model model) {
        String name = "";

        List<Post> posts = postService.getPost(searchForm);

        for(Post post : posts) {
            System.out.println(post.getTitle());
            name = memberService.findById(post.getUid()).get().getName();
            post.setName(name);
        }

        model.addAttribute("posts", posts);

        return "/posts/post_list";
    }
}
