package by.kozlov.epam.myproject.controller.command.user;

import by.kozlov.epam.myproject.controller.Action;
import by.kozlov.epam.myproject.controller.Forward;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.TourService;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import by.kozlov.epam.myproject.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class SelectTourForUser extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
/*        String name = req.getParameter("name");*/
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("currentUser");
            try{
                UserService userService = getServiceFactory().getUserService();
//                TourService tourService = getServiceFactory().getTourService();
//                int tour_id = tourService.findTourIdByName(name).intValue();
                user = userService.findById(user.getId());
                user.setId_tour(id);
                userService.saveIdTour(user);
                return new Forward("/user/selectTour.html");
            }catch (FactoryException | ServiceException e){
                throw new ServletException(e);
            } catch (NumberFormatException e) {
                resp.sendError(404);
            }
            return new Forward("/tourList.html");
        }
        return new Forward("/user/login.html?message="+ URLEncoder.encode("Перезайдите в профиль", "UTF-8"));
    }
}
