package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.category.CategoryDescriptionComparator;
import by.samsolution.pharmacy.comparator.category.CategoryNameComparator;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class MedicamentCategoryDAO implements InterfaceDAO<MedicamentCategory, Long, String, CategorySearchRequest> {
    private Storage<MedicamentCategory> storage;
    private Long ID;

    public MedicamentCategoryDAO() {
        storage = new Storage<>();
        ID = 0L;
    }

    public void setStorage(Storage<MedicamentCategory> storage) {
        this.storage = storage;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public List<MedicamentCategory> getAll() {
        return storage.getItemList();
    }

    @Override
    public List<MedicamentCategory> getAll(CategorySearchRequest request) {
        int from = request.getFrom();
        int size = request.getSize();
        List<MedicamentCategory> categories = getAll();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        List<MedicamentCategory> wantedCategories = new ArrayList<>();
        for (int i = from; i <= last; i++){
            wantedCategories.add(categories.get(i));
        }
        if (request.getSortField() != null){
            switch (request.getSortField()){
                case CATEGORY_NAME:{
                    return wantedCategories.stream().
                            sorted(new CategoryNameComparator()).
                            collect(Collectors.toList());
                }
                case DESCRIPTION:{
                    return wantedCategories.stream().
                            sorted(new CategoryDescriptionComparator()).
                            collect(Collectors.toList());
                }
            }
        }
        return wantedCategories;
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
    public int countOf() {
        return getAll().size();
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
