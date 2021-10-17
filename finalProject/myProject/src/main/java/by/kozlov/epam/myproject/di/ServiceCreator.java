package by.kozlov.epam.myproject.di;

import by.kozlov.epam.myproject.dao.UserDao;
import by.kozlov.epam.myproject.dao.jdbc.JdbcUserDao;
import by.kozlov.epam.myproject.di.exception.ServiceCreatorException;
import by.kozlov.epam.myproject.pool.ConnectionPool;
import by.kozlov.epam.myproject.pool.Exception.ConnectionPoolException;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.impl.UserServiceImpl;

import java.sql.Connection;

public class ServiceCreator implements AutoCloseable{
    private UserService userService = null;
    public UserService getUserService() throws ServiceCreatorException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userService = userServiceImpl;
        }
        return userService;
    }

    private UserDao userDao = null;
    private UserDao getUserDao() throws ServiceCreatorException {
        if (userDao == null) {
            JdbcUserDao jdbcUserDao = new JdbcUserDao();
            jdbcUserDao.setConnection(getConnection());
            userDao = jdbcUserDao;
        }
        return userDao;
    }

    private Connection connection = null;
    private Connection getConnection() throws ServiceCreatorException {
        if (connection == null){
            if (connection == null){
                try {
                    connection = ConnectionPool.getInstance().getConnection();
                } catch (ConnectionPoolException e) {
                    throw new ServiceCreatorException(e);
                }
            }
        }
        return connection;
    }


    @Override
    public void close() throws Exception {
        try {
            connection.close();
        }catch (Exception e) {}
    }
}
