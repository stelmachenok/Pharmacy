package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class MedicamentCategoryDAO implements InterfaceDAO<CategoryDto, Long, String> {
    private Storage<CategoryDto> storage;
    private Long ID;

    public MedicamentCategoryDAO() {
        storage = new Storage<>();
        ID = 0L;
    }

    public void setStorage(Storage<CategoryDto> storage) {
        this.storage = storage;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public List<CategoryDto> getAll() {
        return storage.getItemList();
    }

    @Override
    public CategoryDto getEntityById(Long id) {
        List<CategoryDto> categories = storage.getItemList();
        return categories.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public CategoryDto getEntityByName(String name) {
        List<CategoryDto> categories = storage.getItemList();
        return categories.stream().filter(m -> m.getCategoryName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void update(CategoryDto entity){
        List<CategoryDto> categories = storage.getItemList();
        CategoryDto existedCategory = categories.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        categories.remove(existedCategory);
        categories.add(entity);
    }

    @Override
    public void delete(Long id){
        List<CategoryDto> categories = storage.getItemList();
        CategoryDto existedCategory = categories.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        categories.remove(existedCategory);
    }

    @Override
    public void create(CategoryDto entity) {
        List<CategoryDto> categories = storage.getItemList();
        entity.setId(ID);
        ID++;
        categories.add(entity);
    }
}
