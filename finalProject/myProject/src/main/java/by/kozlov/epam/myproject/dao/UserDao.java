package by.kozlov.epam.myproject.dao;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.entity.User;

import java.util.List;

public interface UserDao extends Dao<Long, User>{

    List<User> findAllUsers() throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

    User findUserByName(String name) throws DaoException;

    String findPasswordByUserId(Long id) throws DaoException;

    boolean updatePasswordByUserId(Long id, String password) throws DaoException;

    void updateNameAndSurname(User user) throws DaoException;

    void updateIdTour(User user) throws DaoException;
}
