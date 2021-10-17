package by.kozlov.epam.myproject.controller.command.user;

import by.kozlov.epam.myproject.controller.Action;
import by.kozlov.epam.myproject.controller.Forward;
import by.kozlov.epam.myproject.entity.Tour;
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

public class SelectTourForUserView extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("currentUser");
            try{
                UserService userService = getServiceFactory().getUserService();
                user = userService.findById(user.getId());
                if (user.getId_tour() != 0) {
                    TourService tourService = getServiceFactory().getTourService();
                    Tour tour = tourService.findById(Long.valueOf(user.getId_tour()));
                    req.setAttribute("tour", tour);
                }
                return null;
            }catch (FactoryException | ServiceException e){
                throw new ServletException(e);
            } catch (NumberFormatException e) {
                resp.sendError(404);
            }
            return null;
        }
        return null;
    }
}
