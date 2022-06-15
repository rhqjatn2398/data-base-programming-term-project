package com.example.databasetermproject.controller;

import com.example.databasetermproject.domain.Member;
import com.example.databasetermproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Slf4j
@Controller
@RequestMapping("/sports")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String registerForm() {
        return "/members/signup";
    }

    @PostMapping("/signup")
    public String register(RegisterMemberForm registerMemberForm) {
        Member member = new Member();
        member.setLoginId(registerMemberForm.getLoginId());
        member.setPassword(registerMemberForm.getPassword());
        member.setName(registerMemberForm.getName());
        member.setNickname(registerMemberForm.getNickname());
        member.setEmailAddress(registerMemberForm.getEmail());

        Member signupMember = memberService.join(member);

        if (signupMember == null) {
            log.info("Signup 실패");
            return "redirect:/sports/signup";
        }

        return "redirect:/sports/login";
    }

}
