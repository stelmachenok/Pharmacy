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
public class MedicamentCategoryDAO implements InterfaceDAO<MedicamentCategory, UUID> {
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
        Supplier<Stream<MedicamentCategory>> supplierStream =
                () -> categories
                        .stream()
                        .filter((c) -> c.getID().equals(id));
        if (supplierStream.get().anyMatch(s -> true)) {
            return supplierStream
                    .get()
                    .findFirst()
                    .get();
        } else {
            return null;
        }
    }

    @Override
    public void update(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        Supplier<Stream<MedicamentCategory>> supplierStream =
                () -> categories
                        .stream()
                        .filter((c) -> c.getID().equals(entity.getID()));
        if (supplierStream.get().anyMatch(s -> true)) {
            categories.remove(supplierStream
                    .get()
                    .findFirst()
                    .get());
            categories.add(entity);
        }
    }

    @Override
    public boolean delete(UUID id) {
        List<MedicamentCategory> categories = storage.getItemList();
        Supplier<Stream<MedicamentCategory>> supplierStream =
                () -> categories
                        .stream()
                        .filter((c) -> c.getID().equals(id));
        if (supplierStream.get().anyMatch(s -> true)) {
            categories.remove(supplierStream
                    .get()
                    .findFirst()
                    .get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(MedicamentCategory entity) {
        List<MedicamentCategory> categories = storage.getItemList();
        Supplier<Stream<MedicamentCategory>> supplierStream =
                () -> categories
                        .stream()
                        .filter((c) -> c.getCategoryName().equals(entity.getCategoryName()) &&
                                c.getDescription().equals(entity.getDescription()));
        if (supplierStream.get().noneMatch(s -> true)){
            categories.add(entity);
            return true;
        }
        else{
            return false;
        }
    }
}
