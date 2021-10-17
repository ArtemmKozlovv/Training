package by.kozlov.epam.myproject.dao;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.entity.Tour;
import by.kozlov.epam.myproject.entity.User;

import java.util.List;

public interface TourDao extends Dao<Long, Tour>{
    List<Tour> findAllTours() throws DaoException;

    Long findTourIdByName(String name) throws DaoException;
}
