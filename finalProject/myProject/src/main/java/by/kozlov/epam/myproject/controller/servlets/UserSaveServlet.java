package by.kozlov.epam.myproject.controller.servlets;

import by.kozlov.epam.myproject.di.ServiceCreator;
import by.kozlov.epam.myproject.di.exception.ServiceCreatorException;
import by.kozlov.epam.myproject.entity.Role;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userSaveServlet", value = "/userSaveServlet.html")
public class UserSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Role role = null;
        try {
            role = Role.valueOf(req.getParameter("role"));
        } catch (NullPointerException | IllegalArgumentException e) {}
        if (login != null && !login.isBlank() && password != null && !password.isBlank()){
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {}
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            try (ServiceCreator creator = new ServiceCreator()) {
                UserService userService = creator.getUserService();
                userService.save(user);
            } catch (ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/userListServlet.html");
    }
}
