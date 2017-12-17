package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.UserConverter;
import by.samsolution.pharmacy.dao.impl.UserJdbcDao;
import by.samsolution.pharmacy.dto.UserDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.UserSearchRequest;
import by.samsolution.pharmacy.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
public class UserServiceImpl implements UserService{
    private UserJdbcDao userDao;
    private UserConverter converter;


    public UserServiceImpl(UserJdbcDao userDao, UserConverter converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public void add(UserDto dto) throws EntityAlreadyExistException {

    }

    @Override
    public void update(UserDto dto) throws EntityNotFoundException, EntityAlreadyExistException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {

    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(u->converter.entityToDto(u))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAll(UserSearchRequest request) {
        return userDao.getAll(request).stream()
                .map(u->converter.entityToDto(u))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        return converter.entityToDto(userDao.getEntityById(id));
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
