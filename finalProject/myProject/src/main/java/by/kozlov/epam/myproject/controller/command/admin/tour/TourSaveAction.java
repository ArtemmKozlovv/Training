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
import java.sql.Date;

public class TourSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        String aboutTour = req.getParameter("aboutTour");
        Date date = null;
        try {
            date = Date.valueOf(req.getParameter("dateSql"));
        } catch (NullPointerException | IllegalArgumentException e) {}

        Long cost = null;
        try {
            cost = Long.valueOf(req.getParameter("cost"));
        } catch (NullPointerException | IllegalArgumentException e) {}
        System.out.println("TEST TourSaveAction before logic");
        if (name != null && country != null && aboutTour != null && date != null){
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {}
            Tour tour = new Tour();
            tour.setId(id);
            tour.setName(name);
            tour.setCountry(country);
            tour.setAboutTour(aboutTour);
            tour.setDateSql(date);
            tour.setCost(cost);
            System.out.println("TEST TourSaveAction IF");
            try {
                System.out.println("TEST TourSaveAction TRY");
                TourService tourService = getServiceFactory().getTourService();
                tourService.save(tour);
            } catch (FactoryException | ServiceException e) {
                throw new ServletException(e);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/tour/list.html");
    }
}
