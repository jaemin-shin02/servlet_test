package main.java.mybatis.mapper;

import main.java.domain.Member;
import main.java.dto.UpdateMemberDto;

import java.util.List;

public interface MemberMapper {
    void insertMember(Member member);
    void updateMember(UpdateMemberDto updateMemberDto);
    void deleteMember(Long memberId);

    Member findById(Long memberId);
    List<Member> findAll();

    List<String> getAllNames();

    List<Member> findByName(String name);
    List<Member> findByEmail(String email);
}
