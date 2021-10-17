package by.kozlov.epam.myproject.service;

import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAll() throws ServiceException;

    User findById(Long id) throws ServiceException;

    User login(String login, String password) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    void saveIdTour(User user) throws ServiceException;

    void create(User user) throws ServiceException;
}
