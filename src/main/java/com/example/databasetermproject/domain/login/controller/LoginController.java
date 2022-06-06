package com.example.databasetermproject.domain.login.controller;

import com.example.databasetermproject.domain.login.service.LoginService;
import com.example.databasetermproject.domain.member.Member;
import com.example.databasetermproject.web.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Controller
@RequestMapping("/sports")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login/login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, HttpServletRequest request) {

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            // TODO : 아이디 또는 비밀번호가 맞지 않음을 알려야 함
            return "/login/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }
}
