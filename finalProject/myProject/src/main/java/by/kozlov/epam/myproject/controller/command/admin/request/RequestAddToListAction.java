package by.kozlov.epam.myproject.controller.command.admin.request;

import by.kozlov.epam.myproject.controller.Action;
import by.kozlov.epam.myproject.controller.Forward;
import by.kozlov.epam.myproject.entity.Request;
import by.kozlov.epam.myproject.entity.Tour;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.RequestService;
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

public class RequestAddToListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("currentUser");
        String number = req.getParameter("number");
        Request request = new Request();
        request.setNumber(number);
        request.setLogin(user.getLogin());
        try{
            UserService userService = getServiceFactory().getUserService();
            User user1 = userService.findById(user.getId());
            TourService tourService = getServiceFactory().getTourService();
            Tour tour = tourService.findById(Long.valueOf(user1.getId_tour()));
            request.setName(tour.getName());
            RequestService requestService = getServiceFactory().getRequestService();
            requestService.save(request);
        } catch (FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
        /*try {
            RequestService requestService = getServiceFactory().getRequestService();
            requestService.save(request);
        } catch (FactoryException | ServiceException e) {
        throw new ServletException(e);
        }*/
        return new Forward("/user/profile.html");
    }
}
