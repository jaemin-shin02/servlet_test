package main.java.mybatis;

import main.java.domain.Member;
import main.java.mybatis.mapper.MemberMapper;
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

            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);

                Member member = Member.createMember("pye", "lov@sup.net");
                memberMapper.insertMember(member);
                Member member1 = Member.createMember("sul", "lov@sup.net");
                memberMapper.insertMember(member1);

                List<String> names = memberMapper.getAllNames();

                for (String name : names) {
                    System.out.println("Name: " + name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
