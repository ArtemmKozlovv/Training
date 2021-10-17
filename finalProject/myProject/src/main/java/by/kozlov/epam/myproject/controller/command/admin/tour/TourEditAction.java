package by.kozlov.epam.myproject.controller.command.admin.tour;

import by.kozlov.epam.myproject.controller.Action;
import by.kozlov.epam.myproject.controller.Forward;
import by.kozlov.epam.myproject.entity.Tour;
import by.kozlov.epam.myproject.service.TourService;
import by.kozlov.epam.myproject.service.exception.ServiceException;
import by.kozlov.epam.myproject.util.FactoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null){
            try{
                TourService tourService = getServiceFactory().getTourService();
                Tour tour = tourService.findById(Long.parseLong(id));
                if (tour != null) {
                    req.setAttribute("tour", tour);
                }else {
                    throw new IllegalArgumentException();
                }
            }catch (FactoryException | ServiceException e){
                throw new ServletException(e);
            } catch (NumberFormatException e) {
                resp.sendError(404);
            }
        }
        return null;
    }
}
