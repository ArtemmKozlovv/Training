package by.kozlov.epam.myproject.controller.servlets;

import by.kozlov.epam.myproject.di.ServiceCreator;
import by.kozlov.epam.myproject.di.exception.ServiceCreatorException;
import by.kozlov.epam.myproject.entity.Role;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.pool.ConnectionPool;
import by.kozlov.epam.myproject.pool.Exception.ConnectionPoolException;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "userListServlet", value = "/userListServlet.html")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false позволяет не создавать сессесию если пользователь не идентифицировался
        if (session != null){
            User user = new User();// (User)session.getAttribute("session_user");
            if (user != null && (user.getRole() == Role.ADMIN || user.getRole() == Role.TRAVEL_AGENT)){
                try(ServiceCreator creator = new ServiceCreator()){
                    UserService userService = creator.getUserService();
                    List<User> users = userService.findAll();
                    req.setAttribute("users", users);
                    req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
                    return;
                }catch (ServiceCreatorException | ServiceException e){
                    throw new ServletException(e);
                } catch (Exception e) {
                    throw new ServletException();
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/loginServlet.html?message=" + URLEncoder.encode("Доступ запрещён", "UTF-8"));
    }
}
