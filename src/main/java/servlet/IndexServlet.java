package servlet;

import model.User;
import service.UserHibernateServiceImpl;
import service.UserJdbcServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = UserHibernateServiceImpl.getInstance().getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/usersindex.jsp").forward(request, response);
    }
}

