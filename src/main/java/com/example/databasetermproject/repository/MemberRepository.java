package com.example.databasetermproject.repository;


import com.example.databasetermproject.domain.Member;

import java.util.Optional;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(long id);
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByNickname(String nickname);
}