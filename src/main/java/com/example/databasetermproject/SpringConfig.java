package com.example.databasetermproject;

import com.example.databasetermproject.domain.member.repository.JdbcTemplateMemberRepository;
import com.example.databasetermproject.domain.member.repository.MemberRepository;
import com.example.databasetermproject.repository.JdbcPostRepository;
import com.example.databasetermproject.repository.PostRepository;
import com.example.databasetermproject.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new JdbcPostRepository(dataSource);
    }

}

