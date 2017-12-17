package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dto.BasicDto;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.SearchRequest;

import java.util.List;

public interface Service<T extends BasicDto, R extends SearchRequest> {
    void add(T dto) throws EntityAlreadyExistException, EntityNotFoundException, DuplicatePrimaryKeyException;

    void update(T dto) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long id) throws EntityNotFoundException;

    List<T> getAll();

    List<T> getAll(R request);

    T getById(Long id);

    int countOf(R request);

    int countOf();
}
