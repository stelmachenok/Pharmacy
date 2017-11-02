package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;
import java.util.UUID;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class MedicamentCategoryDAO implements InterfaceDAO<MedicamentCategory, Long, String> {
    private Storage<MedicamentCategory> storage;
    private Long ID;

    public MedicamentCategoryDAO() {
        storage = new Storage<>();
        ID = 0L;
    }

    @Override
    public List<MedicamentCategory> getAll() {
        return storage.getItemList();
    }

    @Override
    public MedicamentCategory getEntityById(Long id) {
        List<MedicamentCategory> categories = storage.getItemList();
        return categories.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public MedicamentCategory getEntityByName(String name) {
        List<MedicamentCategory> categories = storage.getItemList();
        return categories.stream().filter(m -> m.getCategoryName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void update(MedicamentCategory entity){
        List<MedicamentCategory> categories = storage.getItemList();
        MedicamentCategory existedCategory = categories.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        categories.remove(existedCategory);
        categories.add(entity);
    }

    @Override
    public void delete(Long id){
        List<MedicamentCategory> categories = storage.getItemList();
        MedicamentCategory existedCategory = categories.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        categories.remove(existedCategory);
    }

    @Override
    public void create(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        entity.setId(ID);
        ID++;
        categories.add(entity);
    }
}
