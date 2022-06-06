package com.example.databasetermproject.domain.member.service;

import com.example.databasetermproject.domain.member.Member;
import com.example.databasetermproject.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(Member member) {
        validateDuplicateLoginId(member);
        validateDuplicateNickname(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateLoginId(Member member) {
        memberRepository.findByLoginId(member.getLoginId()).ifPresent(m -> {
            throw new IllegalStateException("이미 해당 로그인 ID가 존재합니다.");
        });
    }

    private void validateDuplicateNickname(Member member) {
        memberRepository.findByNickname(member.getNickname()).ifPresent(m -> {
            throw new IllegalStateException("이미 해당 닉네임이 존재합니다.");
        });
    }
}
