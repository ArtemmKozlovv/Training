package by.kozlov.epam.myproject.service.impl;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.dao.RequestDao;
import by.kozlov.epam.myproject.entity.Request;
import by.kozlov.epam.myproject.service.RequestService;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    private RequestDao requestDao;

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public List<Request> findAll() throws ServiceException {
        try {
            return requestDao.findAllRequests();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Request findById(Long id) throws ServiceException {
        try {
            return requestDao.findEntityById(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Request tour) throws ServiceException {
        try {
            if (tour.getId() != null){
                requestDao.update(tour);
            }else {
                requestDao.create(tour);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids){
                requestDao.delete(id);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
