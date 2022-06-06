package com.example.databasetermproject.domain.login.service;

import com.example.databasetermproject.domain.member.Member;
import com.example.databasetermproject.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    @Autowired
    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member login(String loginId, String password) {

        Optional<Member> result = memberRepository.findByLoginId(loginId).stream().filter(m -> m.getPassword().equals(password)).findAny();

        return result.orElse(null);
    }
}
