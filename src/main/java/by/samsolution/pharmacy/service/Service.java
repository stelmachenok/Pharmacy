package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dto.BasicDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.SearchRequest;

import java.util.List;

public interface Service<T extends BasicDto, R extends SearchRequest> {
    void add(T dto) throws ObjectValidationFailedException, EntityAlreadyExistException;

    void update(T dto) throws ObjectValidationFailedException, EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    List<T> getAll();

    List<T> getAll(R request);

    T getById(Long id);

    int countOf(R request);

    int countOf();
}
