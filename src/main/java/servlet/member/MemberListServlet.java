package main.java.servlet.member;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.domain.Member;
import main.java.mybatis.mapper.MemberMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void init(){
        try {
            // 데이터베이스 설정을 포함한 mybatis-config.xml 로드
            String resource = "main/resources/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // SqlSession을 사용하여 데이터베이스 작업 수행
            MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
            List<Member> members = memberMapper.findAll();

            // 결과를 클라이언트에 전송
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>Members</h1>");
            for (Member member : members) {
                out.println("Name: " + member.getName() + ", Email: " + member.getEmail() + "</p>");
            }
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {
        // 서블릿이 소멸될 때 데이터베이스 연결을 정리
    }

}
