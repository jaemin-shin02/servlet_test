package main.java.servlet.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.domain.Member;
import main.java.dto.LoginRequest;
import main.java.mybatis.mapper.MemberMapper;
import main.java.service.MemberService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void init(){
        try {
            // 데이터베이스 설정을 포함한 mybatis-config.xml 로드
            String resource = "main/resources/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("하이 나 들어옴");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET 요청이 들어왔을 때는 로그인 페이지를 보여줍니다.
        // 일반적으로 로그인 폼이 있는 HTML 페이지를 클라이언트에게 반환합니다.

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Login</h2>");
        out.println("<form action=\"login\" method=\"post\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" id=\"username\" name=\"username\" required><br><br>");
        out.println("<label for=\"email\">Email:</label>");
        out.println("<input type=\"text\" id=\"email\" name=\"email\" required><br><br>");
        out.println("<input type=\"submit\" value=\"Login\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 사용자가 제출한 폼에서 이름과 비밀번호를 가져옴
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        LoginRequest info = new LoginRequest();
        info.setName(username);
        info.setEmail(email);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // SqlSession을 사용하여 데이터베이스 작업 수행
            MemberService memberService = new MemberService(sqlSession);
            Long result = memberService.login(info);

            if (result != null && result > 0) {
                // 로그인 성공 시 세션에 사용자 이름 저장
                HttpSession session = request.getSession();
                session.setAttribute("memberId", request);
                session.setAttribute("username", username);

                System.out.println("성공이다 임마~");

                // 로그인 성공 페이지로 리다이렉트
                response.sendRedirect("/");
            } else {
                // 로그인 실패 시 로그인 페이지로 리다이렉트
                response.sendRedirect("/"); // 실패 시 리다이렉트할 페이지
            }
        }
    }

}
