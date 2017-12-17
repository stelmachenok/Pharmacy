package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.CategoryConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import by.samsolution.pharmacy.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> categoryDAO;
    private CategoryConverter categoryConverter;

    public CategoryServiceImpl(InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> categoryDAO, CategoryConverter categoryConverter) {
        this.categoryDAO = categoryDAO;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public void add(CategoryDto categoryDto) throws EntityAlreadyExistException, DuplicatePrimaryKeyException {
        MedicamentCategory category = categoryConverter.dtoToEntity(categoryDto);
        List<MedicamentCategory> existedCategories = categoryDAO.getEntityByName(categoryDto.getCategoryName());
        if (!equalsCategoriesForAdd(existedCategories, category)) {
            categoryDAO.create(category);
        } else {
            throw new EntityAlreadyExistException("Category with name " + category.getCategoryName() + " already exist!");
        }
    }

    @Override
    public void update(CategoryDto dto) throws EntityNotFoundException, EntityAlreadyExistException {
        MedicamentCategory entity = categoryConverter.dtoToEntity(dto);
        MedicamentCategory existedCategoryEntity = categoryDAO.getEntityById(dto.getId());
        List<MedicamentCategory> existedCategories = categoryDAO.getEntityByName(entity.getCategoryName());
        if (equalsCategoriesforUpdate(existedCategories, entity)){
            throw new EntityAlreadyExistException("Category with same name already exist");
        }
        if (existedCategoryEntity != null) {
            categoryDAO.update(entity);
        } else {
            throw new EntityNotFoundException("Category " + dto + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        MedicamentCategory existedCategoryEntity = categoryDAO.getEntityById(id);
        if (existedCategoryEntity != null) {
            categoryDAO.delete(id);
        } else {
            throw new EntityNotFoundException("Category with id " + id + " doesn't exist");
        }
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryDAO.getAll().stream()
                .map(c -> categoryConverter.entityToDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAll(CategorySearchRequest request) {
        return categoryDAO.getAll(request).stream()
                .map(c -> categoryConverter.entityToDto(c))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryConverter.entityToDto(categoryDAO.getEntityById(id));
    }

    @Override
    public int countOf(CategorySearchRequest request) {
        return categoryDAO.countOf(request);
    }

    @Override
    public int countOf() {
        return categoryDAO.countOf();
    }

    private boolean equalsCategoriesForAdd(List<MedicamentCategory> categories, MedicamentCategory category) {
        if (categories != null && category != null) {
            for (MedicamentCategory c : categories) {
                if (c.getCategoryName().equals(category.getCategoryName()))
                    return true;
            }
        }
        return false;
    }

    private boolean equalsCategoriesforUpdate(List<MedicamentCategory> categories, MedicamentCategory category) {
        if (categories != null && category != null) {
            for (MedicamentCategory c : categories) {
                if (c.getDescription().equals(category.getDescription()) &&
                        c.getCategoryName().equals(category.getCategoryName()))
                    return true;
            }
        }
        return false;
    }
}
