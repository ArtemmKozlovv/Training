package by.kozlov.epam.myproject.controller.command.admin.user;

import by.kozlov.epam.myproject.controller.Action;
import by.kozlov.epam.myproject.controller.Forward;
import by.kozlov.epam.myproject.entity.Role;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import by.kozlov.epam.myproject.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int count_of_tours = 0;
        try {
            count_of_tours = Integer.valueOf(req.getParameter("count_of_tours"));
        } catch (NullPointerException | IllegalArgumentException e) {}
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
            user.setCount_of_tours(Integer.valueOf(count_of_tours));
            try {
                UserService userService = getServiceFactory().getUserService();
                userService.save(user);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}
