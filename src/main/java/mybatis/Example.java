package main.java.mybatis;

import main.java.domain.Coment;
import main.java.domain.Member;
import main.java.domain.Post;
import main.java.dto.PostSearchConditon;
import main.java.dto.UpdateMemberDto;
import main.java.mybatis.mapper.MemberMapper;
import main.java.service.ComentService;
import main.java.service.MemberService;
import main.java.service.PostService;
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
            try (SqlSession session = sqlSessionFactory.openSession()) {
                MemberService memberService = new MemberService(session);
                PostService postService = new PostService(session);
                ComentService comentService = new ComentService(session);

                List<Coment> byPostId = comentService.findByPostId(2L);
                for (Coment coment : byPostId) {
                    System.out.println("coment.getContent() = " + coment.getContent());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}