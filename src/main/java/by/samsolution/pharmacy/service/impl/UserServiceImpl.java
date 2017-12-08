package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.entity.User;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;
import by.samsolution.pharmacy.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Override
    public void add(User dto) throws ObjectValidationFailedException, EntityAlreadyExistException, JdbcManipulationException {

    }

    @Override
    public void update(User dto) throws ObjectValidationFailedException, EntityNotFoundException, JdbcManipulationException, EntityAlreadyExistException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAll(UserSearchRequest request) {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public int countOf(UserSearchRequest request) {
        return 0;
    }

    @Override
    public int countOf() {
        return 0;
    }
}
