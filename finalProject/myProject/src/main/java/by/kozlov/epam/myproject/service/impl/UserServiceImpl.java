package by.kozlov.epam.myproject.service.impl;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.dao.UserDao;
import by.kozlov.epam.myproject.dao.jdbc.JdbcUserDao;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.findEntityById(id);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        }catch (DaoException e){
            throw new ServiceException();
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            if (user.getId() != null){
                userDao.update(user);
            }else {
                userDao.create(user);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids){
                userDao.delete(id);
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(User user) throws ServiceException {
        try {
                userDao.create(user);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public boolean checkLogin(String enterLogin, String enterPass) throws ServiceException {
        boolean flag = false;
        JdbcUserDao userDao = new JdbcUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllUsers();
            for (int i = 0; i < users.size(); i++) {
                if (enterLogin.equals(users.get(i).getLogin())) {
                    if (enterPass.equals(users.get(i).getPassword())) {
                        flag = true;
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Failed to find all users.", e);
        }
        return flag;
    }

    @Override
    public void saveIdTour(User user) throws ServiceException {
        try {
            userDao.updateIdTour(user);
        }catch (DaoException e){
            throw new ServiceException();
        }
    }
}
