package main.java.service;

import main.java.domain.Member;
import main.java.dto.LoginRequest;
import main.java.dto.UpdateMemberDto;
import main.java.mybatis.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MemberService {
    private final SqlSession sqlSession;
    private final MemberMapper memberMapper;

    public MemberService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.memberMapper = sqlSession.getMapper(MemberMapper.class);
    }

    public Long login(LoginRequest request) {
        List<Member> members = memberMapper.findByEmail(request.getEmail());
        if (members.isEmpty()) {
            System.out.println("존재하지 않는 이메일입니다.");
            return -1L; // 존재하지 않는 경우 -1을 반환하도록 수정
        } else {
            Member member = members.get(0);
            if (request.getName().equals(member.getName())) { // 문자열 비교를 위해 equals() 메소드 사용
                // 여기에 비밀번호 검증 등 추가할 수 있음
                return member.getId();
            }
        }
        return -2L; // 이름이 일치하지 않는 경우 다른 값 반환
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