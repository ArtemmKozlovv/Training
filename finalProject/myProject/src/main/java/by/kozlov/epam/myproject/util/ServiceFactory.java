package by.kozlov.epam.myproject.util;

import java.sql.Connection;

import by.kozlov.epam.myproject.dao.RequestDao;
import by.kozlov.epam.myproject.dao.TourDao;
import by.kozlov.epam.myproject.dao.UserDao;
import by.kozlov.epam.myproject.service.RequestService;
import by.kozlov.epam.myproject.service.TourService;
import by.kozlov.epam.myproject.service.UserService;

public interface ServiceFactory extends AutoCloseable {

    UserService getUserService() throws FactoryException;
    TourService getTourService() throws FactoryException;
    RequestService getRequestService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;
    TourDao getTourDao() throws FactoryException;
    RequestDao getRequestDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
