package main.java.service;

import main.java.domain.Member;
import main.java.dto.UpdateMemberDto;
import main.java.mybatis.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MemberService {
    private final SqlSession sqlSession;
    private final MemberMapper memberMapper;

    public MemberService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    public void addMember(Member member) {
        if (memberMapper.findByEmail(member.getEmail()).isEmpty()) {
            memberMapper.insertMember(member);
        } else {
            System.out.println("이미 가입된 이메일입니다.");
        }
        sqlSession.commit();
    }

    public void updateMember(UpdateMemberDto member) {
        memberMapper.updateMember(member);
        sqlSession.commit();
    }

    public void deleteMember(Long memberId) {
        memberMapper.deleteMember(memberId);
        sqlSession.commit();
    }

    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    public List<Member> findAll(){
        return memberMapper.findAll();
    }

    public List<Member> findByName(String name) {
        return memberMapper.findByName(name);
    }

    public List<Member> findByEmail(String email) {
        return memberMapper.findByEmail(email);
    }
}