package by.kozlov.epam.myproject.controller;

import by.kozlov.epam.myproject.di.ServiceCreator;
import by.kozlov.epam.myproject.di.exception.ServiceCreatorException;
import by.kozlov.epam.myproject.entity.Role;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import by.kozlov.epam.myproject.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterAction extends Action {


    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String PARAM_NAME_LOGIN = "login";

        final String PARAM_NAME_PASSWORD = "password";

        final String PARAM_NAME_NAME = "name";

        final String PARAM_NAME_SURNAME = "surname";

        String name = req.getParameter(PARAM_NAME_NAME);
        String surname = req.getParameter(PARAM_NAME_SURNAME);
        String login = req.getParameter(PARAM_NAME_LOGIN);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        if (login != null && !login.isBlank() && password != null && !password.isBlank() && name != null && !name.isBlank() && surname != null && !surname.isBlank()) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(Role.USER);
            user.setCount_of_tours(0);
            try {
                UserService userService = getServiceFactory().getUserService();
                userService.create(user);
                return new Forward("/login.html");
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
