package by.kozlov.epam.myproject.service.impl;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;

import by.kozlov.epam.myproject.dao.TourDao;
import by.kozlov.epam.myproject.entity.Tour;
import by.kozlov.epam.myproject.service.TourService;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.util.List;

public class TourServiceImpl implements TourService {

    private TourDao tourDao;

    public void setTourDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public List<Tour> findAll() throws ServiceException {
        try {
            return tourDao.findAllTours();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tour findById(Long id) throws ServiceException {
        try {
            return tourDao.findEntityById(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Tour tour) throws ServiceException {
        try {
            if (tour.getId() != null){
                tourDao.update(tour);
            }else {
                tourDao.create(tour);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids){
                tourDao.delete(id);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Long findTourIdByName(String name) throws ServiceException {
        try {
            return tourDao.findTourIdByName(name);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
