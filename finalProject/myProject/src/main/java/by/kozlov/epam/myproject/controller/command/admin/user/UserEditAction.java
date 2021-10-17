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

public class UserEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null){
            try{
                UserService userService = getServiceFactory().getUserService();
                User user = userService.findById(Long.parseLong(id));
                if (user != null) {
                    req.setAttribute("user", user);
                }else {
                    throw new IllegalArgumentException();
                }
            }catch (FactoryException | ServiceException e){
                throw new ServletException(e);
            } catch (NumberFormatException e) {
                resp.sendError(404);
            }
        }
        req.setAttribute("roles", Role.values());
        return null;
    }
}
