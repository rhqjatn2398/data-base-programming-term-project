package com.example.databasetermproject.domain.member.repository;

import com.example.databasetermproject.domain.Member;
import com.example.databasetermproject.repository.JdbcTemplateMemberRepository;
import com.example.databasetermproject.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JdbcTemplateMemberRepositoryTest {

    JdbcTemplateMemberRepository jdbcTemplateMemberRepository;

    @Autowired
    public JdbcTemplateMemberRepositoryTest(MemberRepository memberRepository) {
        this.jdbcTemplateMemberRepository = (JdbcTemplateMemberRepository) memberRepository;
    }

    @Test
    void save() {
        Member member1 = new Member();
        member1.setLoginId("qwer1234");
        member1.setPassword("98765421");
        member1.setName("둘리");
        member1.setNickname("둘리가계속되면호의");
        member1.setEmailAddress("gwewe");

        Member result = jdbcTemplateMemberRepository.save(member1);

        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    void findById() {
        Member member1 = new Member();
        member1.setLoginId("qwer1234");
        member1.setPassword("98765421");
        member1.setName("둘리");
        member1.setNickname("둘리가계속되면호의");
        member1.setEmailAddress("enffl12345@gmail.com");

        Member member2 = new Member();
        member2.setLoginId("asdf9876");
        member2.setPassword("98765421");
        member2.setName("또치");
        member2.setNickname("또치가계속되면호의");
        member2.setEmailAddress("Ehcl1111@gmail.com");

        Member member3 = new Member();
        member3.setLoginId("ghdrlfehd1200");
        member3.setPassword("98765421");
        member3.setName("고길동");
        member3.setNickname("소드마스터고길동");
        member3.setEmailAddress("rjatjd9899@gmail.com");

        jdbcTemplateMemberRepository.save(member1);
        jdbcTemplateMemberRepository.save(member2);
        jdbcTemplateMemberRepository.save(member3);

        Member result = jdbcTemplateMemberRepository.findById(member1.getId()).orElse(null);

        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    void findByLoginId() {
        Member member1 = new Member();
        member1.setLoginId("qwer1234");
        member1.setPassword("98765421");
        member1.setName("둘리");
        member1.setNickname("둘리가계속되면호의");
        member1.setEmailAddress("enffl12345@gmail.com");

        Member member2 = new Member();
        member2.setLoginId("asdf9876");
        member2.setPassword("98765421");
        member2.setName("또치");
        member2.setNickname("또치가계속되면호의");
        member2.setEmailAddress("Ehcl1111@gmail.com");

        Member member3 = new Member();
        member3.setLoginId("ghdrlfehd1200");
        member3.setPassword("98765421");
        member3.setName("고길동");
        member3.setNickname("소드마스터고길동");
        member3.setEmailAddress("rjatjd9899@gmail.com");

        jdbcTemplateMemberRepository.save(member1);
        jdbcTemplateMemberRepository.save(member2);
        jdbcTemplateMemberRepository.save(member3);

        Member result = jdbcTemplateMemberRepository.findByLoginId(member1.getLoginId()).orElse(null);

        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    void findByNickname() {
        Member member1 = new Member();
        member1.setLoginId("qwer1234");
        member1.setPassword("98765421");
        member1.setName("둘리");
        member1.setNickname("둘리가계속되면호의");
        member1.setEmailAddress("enffl12345@gmail.com");

        Member member2 = new Member();
        member2.setLoginId("asdf9876");
        member2.setPassword("98765421");
        member2.setName("또치");
        member2.setNickname("또치가계속되면호의");
        member2.setEmailAddress("Ehcl1111@gmail.com");

        Member member3 = new Member();
        member3.setLoginId("ghdrlfehd1200");
        member3.setPassword("98765421");
        member3.setName("고길동");
        member3.setNickname("소드마스터고길동");
        member3.setEmailAddress("rjatjd9899@gmail.com");

        jdbcTemplateMemberRepository.save(member1);
        jdbcTemplateMemberRepository.save(member2);
        jdbcTemplateMemberRepository.save(member3);

        Member result = jdbcTemplateMemberRepository.findByNickname(member1.getNickname()).orElse(null);

        Assertions.assertThat(member1).isEqualTo(result);
    }
}