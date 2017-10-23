package DAO.impl;

import DAO.InterfaceDAO;
import entity.MedicamentCategory;
import storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class MedicamentCategoryDAO implements InterfaceDAO<MedicamentCategory, Integer> {
    private Storage<MedicamentCategory> storage;

    public MedicamentCategoryDAO() {
        storage = new Storage<>();
    }

    @Override
    public List<MedicamentCategory> getAll() {
        return storage.getItemList();
    }

    @Override
    public MedicamentCategory getEntityById(Integer id) {
        List<MedicamentCategory> categories = storage.getItemList();
        for (MedicamentCategory category : categories) {
            if (category.getID() == id){
                return category;
            }
        }
        return null;
    }

    @Override
    public void update(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        for (int i = 0; i < categories.size(); i++) {
            MedicamentCategory category = categories.get(i);
            if (category.getID() == entity.getID()) {
                categories.remove(i);
                categories.add(entity);
                break;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        List<MedicamentCategory> categories = storage.getItemList();
        for (int i = 0; i < categories.size(); i++) {
            MedicamentCategory category = categories.get(i);
            if (category.getID() == id) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean create(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        for (MedicamentCategory category : categories) {
            if (category.getCategoryName().equals(entity.getCategoryName()) &&
                    category.getDescription().equals(entity.getDescription()) &&
                    category.getID() == entity.getID()){
                return false;
            }
        }
        categories.add(entity);
        return true;
    }
}
