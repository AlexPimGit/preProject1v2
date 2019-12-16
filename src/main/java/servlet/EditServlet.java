package servlet;

import model.User;
import service.UserHibernateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    //private UserServiceImpl userService = new UserJdbcServiceImpl(); //for JDBC

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
//          String name = request.getParameter("name");
            User user = UserHibernateServiceImpl.getInstance().getUserById(id);
            if (user != null) {
                request.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("/notfound.jsp").forward(request, response);//получать диспетчер из реквеста
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");

        User user = new User(id, name, nickname);
        UserHibernateServiceImpl.getInstance().changeUser(user);
        response.sendRedirect(request.getContextPath() + "/");
    }
}

