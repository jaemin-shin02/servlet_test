package main.java.servlet.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import java.util.List;

@WebServlet("/post")
public class PostList extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            PostService postService = new PostService(sqlSession);

            int totalCount = postService.totalCount();


            // 페이징 처리를 위한 변수 설정
            int page = 1; // 기본 페이지는 1로 설정
            int pageSize = 3; // 페이지당 게시물 수

            // 클라이언트로부터 페이지 번호를 받아옴
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
            }

            // 페이징 처리된 게시물 가져오기
            List<Post> postList = postService.page((page - 1)*pageSize, pageSize);

            // 전체 게시물 수와 전체 페이지 수 가져오기
            int totalPosts = postService.totalCount();
            int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

            // HTML로 응답 작성
            out.println("<html>");
            out.println("<head><title>게시판</title></head>");
            out.println("<body>");
            out.println("<h1>게시판</h1>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>제목</th>");
            out.println("<th>내용</th>");
            out.println("<th>작성자</th>");
            out.println("<th>좋아요</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (Post post : postList) {
                out.println("<tr>");
                out.println("<td>" + post.getTitle() + "</td>");
                out.println("<td>" + post.getContent() + "</td>");
                out.println("<td>" + post.getMemberId() + "</td>");
                out.println("<td>" + post.getLikes() + "</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");

            // 페이징 링크 출력
            out.println("<div>");
            out.println("총 " + totalPosts + "개의 게시물 중 " + page + "페이지를 보고 있습니다.");
            String currentUrl = request.getRequestURI();
            if (page > 1) {
                out.println("<a href='" + currentUrl + "?page=" + (page - 1) + "'>이전 페이지</a>");
            }
            if (page < totalPages) {
                out.println("<a href='" + currentUrl + "?page=" + (page + 1) + "'>다음 페이지</a>");
            }
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        }
    }
}