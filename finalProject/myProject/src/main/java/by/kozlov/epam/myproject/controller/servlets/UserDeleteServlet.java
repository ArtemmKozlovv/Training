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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userDeleteServlet", value = "/userDeleteServlet.html")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<Long> ids = new ArrayList<>(idsStr.length);
        try (ServiceCreator creator = new ServiceCreator()) {
            for (String id : idsStr){
                ids.add(Long.valueOf(id));
            }
            UserService userService = creator.getUserService();
            userService.delete(ids);
        } catch (NumberFormatException e){
        }catch (ServiceCreatorException | ServiceException e) {
            throw new ServletException(e);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/userListServlet.html");
    }
}
