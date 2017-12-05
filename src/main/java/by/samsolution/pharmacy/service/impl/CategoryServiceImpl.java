package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.CategoryConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import by.samsolution.pharmacy.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {
    private InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> categoryDAO;
    private CategoryConverter categoryConverter;

    public CategoryServiceImpl(InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> categoryDAO, CategoryConverter categoryConverter) {
        this.categoryDAO = categoryDAO;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public void add(CategoryDto categoryDto) throws ObjectValidationFailedException, EntityAlreadyExistException, JdbcManipulationException {
        MedicamentCategory category = categoryConverter.dtoToEntity(categoryDto);
        List<MedicamentCategory> existedCategories = categoryDAO.getEntityByName(categoryDto.getCategoryName());
        if (!equalsCategories(existedCategories, category)) {
            categoryDAO.create(category);
        } else {
            throw new EntityAlreadyExistException("Category " + category + " already exist!");
        }
    }

    @Override
    public void update(CategoryDto dto) throws ObjectValidationFailedException, EntityNotFoundException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {

    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryDAO.getAll().stream()
                .map(c -> categoryConverter.entityToDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAll(CategorySearchRequest request) {
        return null;
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryConverter.entityToDto(categoryDAO.getEntityById(id));
    }

    @Override
    public int countOf(CategorySearchRequest request) {
        return 0;
    }

    @Override
    public int countOf() {
        return 0;
    }

    private boolean equalsCategories(List<MedicamentCategory> categories, MedicamentCategory category) {
        if (categories != null && category != null){
            for (MedicamentCategory c:categories) {
                if (c.getDescription().equals(category.getDescription()) &&
                        c.getCategoryName().equals(category.getCategoryName()))
                    return true;
            }
        }
        return false;
    }
}
