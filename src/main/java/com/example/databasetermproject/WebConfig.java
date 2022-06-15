package com.example.databasetermproject;

import com.example.databasetermproject.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).order(1).addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/img/**", "/", "/sports/signup", "/sports/login", "/sports/logout", "/posts/search");
    }
}
