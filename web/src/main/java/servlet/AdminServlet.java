package servlet;

import service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-page")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("admin", AdminService.getInstance().getAdminById(1L));
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/login-page.jsp")
                .forward(req, resp);
    }
}
