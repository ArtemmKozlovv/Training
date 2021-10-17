package by.kozlov.epam.myproject.controller.servlets;

import by.kozlov.epam.myproject.command.exception.CommandException;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "registerServlet", value = "/registerServlet.html")
public class RegisterServlet extends HttpServlet {
    private static final String PARAM_NAME_LOGIN = "login";

    private static final String PARAM_NAME_PASSWORD = "password";

    private static final String PARAM_NAME_NAME = "name";

    private static final String PARAM_NAME_SURNAME = "surname";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        if (login != null && !login.isBlank() && password != null && !password.isBlank() && name != null && !name.isBlank() && surname != null && !surname.isBlank()) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException e) {
            }
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(Role.USER);
            try (ServiceCreator creator = new ServiceCreator()) {
                UserService userService = creator.getUserService();
                userService.save(user);
            } catch (ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }
}