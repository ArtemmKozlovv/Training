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

@WebServlet(name = "userEditServlet", value = "/userEditServlet.html")
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null){
            try(ServiceCreator creator = new ServiceCreator()){
                UserService userService = creator.getUserService();
                User user = userService.findById(Long.parseLong(id));
                if (user != null) {
                    req.setAttribute("user", user);
                }else {
                    throw new IllegalArgumentException();
                }
            }catch (ServiceCreatorException | ServiceException e){
                throw new ServletException(e);
            } catch (NumberFormatException e) {
                resp.sendError(404);
                return;
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
    }
}
