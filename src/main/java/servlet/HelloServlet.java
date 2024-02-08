package main.java.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Welcome</title>");
        out.println("</head>");
        out.println("<body>");
        if (username != null && !username.isEmpty()) {
            // 로그인 상태일 때
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("<p>This is a protected page.</p>");
            out.println("<form action=\"/logout\" method=\"post\">");
            out.println("<a href=\"/createPost\">New Post</a>");
            out.println("<a href=\"/memberList\">Users</a>");
            out.println("<input type=\"submit\" value=\"Logout\">");
            out.println("</form>");
        } else {
            // 로그인되지 않은 상태일 때
            out.println("<h2>Welcome to My Website</h2>");
            out.println("<p>Please login to access the content.</p>");
            out.println("<a href=\"/login\">Login</a>");
            out.println("<a href=\"/join\">Join</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그아웃 처리
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        // 로그아웃 후 로그인 페이지로 리다이렉트
        response.sendRedirect("/");
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
