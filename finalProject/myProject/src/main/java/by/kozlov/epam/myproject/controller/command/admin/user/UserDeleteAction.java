package by.kozlov.epam.myproject.controller.command.admin.user;

import by.kozlov.epam.myproject.controller.Action;
import by.kozlov.epam.myproject.controller.Forward;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import by.kozlov.epam.myproject.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<Long> ids = new ArrayList<>(idsStr.length);
        try{
            for (String id : idsStr){
                ids.add(Long.valueOf(id));
            }
            UserService userService = getServiceFactory().getUserService();
            userService.delete(ids);
        } catch (NumberFormatException e){
        }catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        return new Forward("/user/list.html");
    }
}
