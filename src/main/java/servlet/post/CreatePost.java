package main.java.servlet.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.domain.Post;
import main.java.dto.LoginRequest;
import main.java.service.MemberService;
import main.java.service.PostService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/createPost")
public class CreatePost extends HttpServlet {
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
        out.println("<title>Create Post</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Create New Post</h2>");
        out.println("<form action=\"/createPost\" method=\"post\">");
        out.println("<label for=\"title\">Title:</label><br>");
        out.println("<input type=\"text\" id=\"title\" name=\"title\" required><br><br>");
        out.println("<label for=\"content\">Content:</label><br>");
        out.println("<textarea id=\"content\" name=\"content\" rows=\"4\" cols=\"50\" required></textarea><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 사용자가 제출한 폼에서 이름과 비밀번호를 가져옴
        String title = request.getParameter("title");
        String content = request.getParameter("content");


        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // SqlSession을 사용하여 데이터베이스 작업 수행
            MemberService memberService = new MemberService(sqlSession);
            PostService postService = new PostService(sqlSession);

            HttpSession session = request.getSession();
            Long memberId = (Long) session.getAttribute("memberId");
            postService.addPost(Post.createPost(memberId, title, content));

            response.sendRedirect("/");
        }

    }
}
