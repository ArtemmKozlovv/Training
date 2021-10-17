package by.kozlov.epam.myproject.controller;

import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import by.kozlov.epam.myproject.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction extends Action {


    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && !login.isBlank() && password != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.login(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    return new Forward("/index.html");
                }else {
                    return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }else {
            return null;
        }
    }
}
