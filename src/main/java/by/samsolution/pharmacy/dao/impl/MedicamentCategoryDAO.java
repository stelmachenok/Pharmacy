package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class MedicamentCategoryDAO implements InterfaceDAO<MedicamentCategory, UUID, String> {
    private Storage<MedicamentCategory> storage;

    public MedicamentCategoryDAO() {
        storage = new Storage<>();
    }

    @Override
    public List<MedicamentCategory> getAll() {
        return storage.getItemList();
    }

    @Override
    public MedicamentCategory getEntityById(UUID id) {
        List<MedicamentCategory> categories = storage.getItemList();
        return categories.stream().filter(m -> m.getID().equals(id)).findAny().orElse(null);
    }

    @Override
    public MedicamentCategory getEntityByName(String name) {
        List<MedicamentCategory> categories = storage.getItemList();
        return categories.stream().filter(m -> m.getCategoryName().equals(name)).findAny().orElse(null);
    }

    @Override
    public boolean update(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        MedicamentCategory existedCategory = categories.stream().filter(m -> m.getCategoryName().equals(entity.getCategoryName())).findAny().orElse(null);
        if (existedCategory != null) {
            categories.remove(existedCategory);
            categories.add(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String name) {
        List<MedicamentCategory> categories = storage.getItemList();
        MedicamentCategory existedCategory = categories.stream().filter(m -> m.getCategoryName().equals(name)).findAny().orElse(null);
        if (existedCategory!= null) {
            categories.remove(existedCategory);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void create(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        categories.add(entity);
    }
}
