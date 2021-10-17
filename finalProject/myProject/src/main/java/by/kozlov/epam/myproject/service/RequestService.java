package by.kozlov.epam.myproject.service;

import by.kozlov.epam.myproject.entity.Request;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.util.List;

public interface RequestService {
    List<Request> findAll() throws ServiceException;

    Request findById(Long id) throws ServiceException;

    void save(Request request) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;
}
