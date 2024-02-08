package main.java.servlet.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.domain.Member;
import main.java.domain.Post;
import main.java.service.MemberService;
import main.java.service.PostService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Join</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Welcome Join!!!!</h2>");
        out.println("<form action=\"/join\" method=\"post\">");
        out.println("<label for=\"name\">Username:</label><br>");
        out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
        out.println("<label for=\"email\">Email:</label><br>");
        out.println("<input type=\"text\" id=\"email\" name=\"email\" required><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MemberService memberService = new MemberService(sqlSession);

            if (memberService.addMember(Member.createMember(name, email))) {
                response.sendRedirect("/");
                System.out.println("회원가입 성공");
            } else {
                response.sendRedirect("/");
                System.out.println("회원가입 실패");
            }
        }
    }

}
