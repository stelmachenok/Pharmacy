package by.samsolution.pharmacy.dao;

import by.samsolution.pharmacy.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public interface InterfaceDAO<E, K, N> {
    List<E> getAll();

    E getEntityById(K id);

    E getEntityByName(N name);

    void update(E entity) throws EntityNotFoundException;

    void delete(K id) throws EntityNotFoundException;

    void create(E entity);
}
