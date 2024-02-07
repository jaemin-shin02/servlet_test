package main.java.mybatis.mapper;

import main.java.domain.Member;

import java.util.List;

public interface MemberMapper {
    void insertMember(Member member);
    void updateMember(Member member);
    void deleteMember(Long memberId);
    List<String> getAllNames();

    List<Member> findByName(String name);
    List<Member> findByEmail(String email);
}
