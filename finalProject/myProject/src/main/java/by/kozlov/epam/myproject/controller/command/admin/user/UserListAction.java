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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class UserListAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false позволяет не создавать сессесию если пользователь не идентифицировался
        if (session != null){
            User user = (User)session.getAttribute("currentUser");
            if (user != null && (user.getRole() == Role.ADMIN)){
                try{
                    UserService userService = getServiceFactory().getUserService();
                    List<User> users = userService.findAll();
                    req.setAttribute("users", users);
                    return null;
                }catch (FactoryException | ServiceException e){
                    throw new ServletException(e);
                }
            }
        }
        return new Forward(("/login.html?message=" + URLEncoder.encode("Доступ запрещён", "UTF-8")));
    }
}
