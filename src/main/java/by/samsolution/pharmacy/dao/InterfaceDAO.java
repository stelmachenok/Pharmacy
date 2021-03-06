package by.samsolution.pharmacy.dao;

import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.AbstractSearchRequest;

import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public interface InterfaceDAO<E, K, N, R extends AbstractSearchRequest> {
    List<E> getAll();

    List<E> getAll(R request);

    E getEntityById(K id);

    List<E> getEntityByName(N name);

    int countOf();

    int countOf(R request);

    void update(E entity) throws EntityNotFoundException;

    void delete(K id) throws EntityNotFoundException;

    void create(E entity) throws DuplicatePrimaryKeyException;
}
