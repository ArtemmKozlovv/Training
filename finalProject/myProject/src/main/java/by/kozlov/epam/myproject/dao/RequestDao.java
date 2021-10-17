package by.kozlov.epam.myproject.dao;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.entity.Request;

import java.util.List;

public interface RequestDao extends Dao<Long, Request>{
    List<Request> findAllRequests() throws DaoException;
}
