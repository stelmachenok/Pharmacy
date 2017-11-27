package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.ComparatorChooser;
import by.samsolution.pharmacy.comparator.category.CategoryDescriptionComparator;
import by.samsolution.pharmacy.comparator.category.CategoryNameComparator;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.searchrequest.impl.CategorySearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
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
        List<MedicamentCategory> categories = getAll();
        List<MedicamentCategory> wantedCategories = new ArrayList<>();
        ComparatorChooser chooser = new ComparatorChooser();
        categories = (List<MedicamentCategory>) categories.stream().
                sorted(chooser.choose(request.getSortField())).
                collect(Collectors.toList());
        if (!request.getDirection()){
            Collections.reverse(categories);
        }
        int from = request.getFrom();
        int size = request.getSize();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        for (int i = from; i <= last; i++) {
            wantedCategories.add(categories.get(i));
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
    public void update(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        MedicamentCategory existedCategory = categories.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        categories.remove(existedCategory);
        categories.add(entity);
    }

    @Override
    public void delete(Long id) {
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
