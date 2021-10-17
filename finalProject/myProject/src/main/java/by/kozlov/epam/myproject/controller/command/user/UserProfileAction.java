package by.kozlov.epam.myproject.controller.command.user;

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


public class UserProfileAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        String id = req.getParameter("id");
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
        return null;*/
        HttpSession session = req.getSession(false);
        System.out.println("session = null");
        if(session != null) {
            System.out.println("session != null");
            User user1 = (User)session.getAttribute("currentUser");
            String id = user1.getId() + "";
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
            return null;
        }
        return new Forward("/user/login.html?message="+ URLEncoder.encode("Перезайдите в профиль", "UTF-8"));
    }
}
