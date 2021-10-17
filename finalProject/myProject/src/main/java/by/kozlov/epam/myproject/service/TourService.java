package by.kozlov.epam.myproject.service;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.entity.Tour;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.util.List;

public interface TourService {
    List<Tour> findAll() throws ServiceException;

    Tour findById(Long id) throws ServiceException;

    void save(Tour tour) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    Long findTourIdByName(String name) throws ServiceException;
}
