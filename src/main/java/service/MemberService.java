package main.java.service;

import main.java.domain.Member;
import main.java.mybatis.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MemberService {
    private final SqlSessionFactory sqlSessionFactory;

    public MemberService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void addMember(Member member) {
        List<Member> byEmail = findByEmail(member.getEmail());
        if (!byEmail.isEmpty()) {
            System.out.println("이미 가입된 이메일입니다.");
            return;
        }
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper memberMapper = session.getMapper(MemberMapper.class);
            memberMapper.insertMember(member);
            session.commit();
        }
    }

    public void updateMember(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper memberMapper = session.getMapper(MemberMapper.class);
            memberMapper.updateMember(member);
            session.commit();
        }
    }

    public void deleteMember(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper memberMapper = session.getMapper(MemberMapper.class);
            memberMapper.deleteMember(member.getId());
            session.commit();
        }
    }

    public List<Member> findByName(String name) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper memberMapper = session.getMapper(MemberMapper.class);
            List<Member> byName = memberMapper.findByName(name);
            return byName;
        }
    }

    public List<Member> findByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper memberMapper = session.getMapper(MemberMapper.class);
            List<Member> byEmail = memberMapper.findByEmail(email);
            return byEmail;
        }
    }
}
