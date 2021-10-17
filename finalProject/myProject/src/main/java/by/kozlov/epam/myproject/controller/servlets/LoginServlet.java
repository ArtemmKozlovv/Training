package by.kozlov.epam.myproject.controller.servlets;

import by.kozlov.epam.myproject.di.ServiceCreator;
import by.kozlov.epam.myproject.di.exception.ServiceCreatorException;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "loginServlet", value = "/loginServlet.html")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && !login.isBlank() && password != null) {
            try (ServiceCreator creator = new ServiceCreator()) {
                UserService userService = creator.getUserService();
                User user = userService.login(login, password);
                if (user != null){
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
                    resp.sendRedirect(req.getContextPath() + "/mainPageServlet.html");
                    /*if (user.getRole() == Role.ADMIN && user.getRole() == Role.TRAVEL_AGENT) {
                        resp.sendRedirect(req.getContextPath() + "/mainPageServlet");
                    }else if(user.getRole() == Role.USER) {
                        resp.sendRedirect(req.getContextPath() + "/loginServlet?message=" + URLEncoder.encode(String.format("Пользователь %s (%s) вошёл в систему",user.getLogin(), user.getRole().getName()),"UTF-8"));
                    }*/
                    return;
                }
            } catch (ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/loginServlet.html?message=" + URLEncoder.encode("Логин или пароль не опознаны","UTF-8"));
    }
}
