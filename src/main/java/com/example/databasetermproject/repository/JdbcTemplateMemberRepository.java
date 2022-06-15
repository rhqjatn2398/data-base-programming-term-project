package com.example.databasetermproject.repository;

import com.example.databasetermproject.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Slf4j
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("loginId", member.getLoginId());
        parameters.put("password", member.getPassword());
        parameters.put("name", member.getName());
        parameters.put("nickname", member.getNickname());
        parameters.put("emailAddress", member.getEmailAddress());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        log.info("member = {}", member);

        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return jdbcTemplate.query("select * from member where loginId = ?", memberRowMapper(), loginId).stream().findFirst();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return jdbcTemplate.query("select * from member where nickname = ?", memberRowMapper(), nickname).stream().findFirst();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setLoginId(rs.getString("loginId"));
            member.setPassword(rs.getString("password"));
            member.setName(rs.getString("name"));
            member.setNickname(rs.getString("nickname"));
            member.setEmailAddress(rs.getString("emailAddress"));

            return member;
        };
    }
}
