package main.java.mybatis;

import main.java.domain.Member;
import main.java.mybatis.mapper.MemberMapper;
import main.java.service.MemberService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        try (Reader reader = Resources.getResourceAsReader("main/resources/mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            MemberService memberService = new MemberService(sqlSessionFactory);

            Member member = Member.createMember("pye", "pye.eun@naver.com");
            memberService.addMember(member);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
