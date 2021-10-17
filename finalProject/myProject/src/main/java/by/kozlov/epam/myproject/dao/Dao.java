package by.kozlov.epam.myproject.dao;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.entity.Entity;

public interface Dao<K, T extends Entity> {

    T findEntityById(K id) throws DaoException;

    void delete(K id) throws DaoException;

    Long create(T entity) throws DaoException;

    void update(T entity) throws DaoException;
}
