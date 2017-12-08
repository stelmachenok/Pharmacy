package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.entity.User;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;

import java.util.List;

public interface UserService extends Service<User, UserSearchRequest> {
}
