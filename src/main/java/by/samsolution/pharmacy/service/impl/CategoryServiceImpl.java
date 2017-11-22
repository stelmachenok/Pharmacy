package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import by.samsolution.pharmacy.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    @Override
    public void add(CategoryDto dto) throws ObjectValidationFailedException, EntityAlreadyExistException {

    }

    @Override
    public void update(CategoryDto dto) throws ObjectValidationFailedException, EntityNotFoundException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {

    }

    @Override
    public List<CategoryDto> getAll() {
        return null;
    }

    @Override
    public List<CategoryDto> getAll(CategorySearchRequest request) {
        return null;
    }

    @Override
    public int countOf(CategorySearchRequest request) {
        return 0;
    }

    @Override
    public int countOf() {
        return 0;
    }
}
