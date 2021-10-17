package by.kozlov.epam.myproject.util;

import java.sql.Connection;

import by.kozlov.epam.myproject.dao.RequestDao;
import by.kozlov.epam.myproject.dao.TourDao;
import by.kozlov.epam.myproject.dao.UserDao;
import by.kozlov.epam.myproject.dao.jdbc.JdbcRequestDao;
import by.kozlov.epam.myproject.dao.jdbc.JdbcTourDao;
import by.kozlov.epam.myproject.dao.jdbc.JdbcUserDao;
import by.kozlov.epam.myproject.pool.ConnectionPool;
import by.kozlov.epam.myproject.pool.Exception.ConnectionPoolException;
import by.kozlov.epam.myproject.service.RequestService;
import by.kozlov.epam.myproject.service.TourService;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.impl.RequestServiceImpl;
import by.kozlov.epam.myproject.service.impl.TourServiceImpl;
import by.kozlov.epam.myproject.service.impl.UserServiceImpl;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public TourService getTourService() throws FactoryException {
        TourServiceImpl tourService = new TourServiceImpl();
        tourService.setTourDao(getTourDao());
        return tourService;
    }

    @Override
    public RequestService getRequestService() throws FactoryException {
        RequestServiceImpl requestService = new RequestServiceImpl();
        requestService.setRequestDao(getRequestDao());
        return requestService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        JdbcUserDao userDao = new JdbcUserDao();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public TourDao getTourDao() throws FactoryException {
        JdbcTourDao tourDao = new JdbcTourDao();
        tourDao.setConnection(getConnection());
        return tourDao;
    }

    @Override
    public RequestDao getRequestDao() throws FactoryException {
        JdbcRequestDao requestDao = new JdbcRequestDao();
        requestDao.setConnection(getConnection());
        return requestDao;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch(ConnectionPoolException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch(Exception e) {}
    }
}
