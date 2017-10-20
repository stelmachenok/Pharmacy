package DAO;

import entity.Medicament;

import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public interface InterfaceDAO<E, K> {
    List<E> getAll();

    E getEntityById(K id);

    void update(E entity);

    boolean delete(K id);

    boolean create(E entity);
}
