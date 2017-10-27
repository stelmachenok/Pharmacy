package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<Medicament, UUID, String> {
    private Storage<Medicament> storage;

    public MedicamentDAO() {
        storage = new Storage<>();
    }

    @Override
    public List<Medicament> getAll() {
        return storage.getItemList();
    }

    @Override
    public Medicament getEntityById(UUID id) {
        List<Medicament> medicaments = storage.getItemList();
        Supplier<Stream<Medicament>> supplierStream =
                () -> medicaments
                        .stream()
                        .filter((m) -> m.getGUID().equals(id));
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
    public Medicament getEntityByName(String name) {
        List<Medicament> medicaments = storage.getItemList();
        Supplier<Stream<Medicament>> supplierStream =
                () -> medicaments
                        .stream()
                        .filter((m) -> m.getGUID().equals(name));
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
    public void update(Medicament entity) {
        List<Medicament> medicaments = storage.getItemList();
        Supplier<Stream<Medicament>> supplierStream =
                () -> medicaments
                        .stream()
                        .filter((m) -> m.getGUID().equals(entity.getGUID()));
        if (supplierStream.get().anyMatch(s -> true)) {
            medicaments.remove(supplierStream
                    .get()
                    .findFirst()
                    .get());
            medicaments.add(entity);
        }
    }

    @Override
    public boolean delete(UUID id) {
        List<Medicament> medicaments = storage.getItemList();
        Supplier<Stream<Medicament>> supplierStream =
                () -> medicaments
                        .stream()
                        .filter((m) -> m.getGUID().equals(id));
        if (supplierStream.get().anyMatch(s -> true)) {
            medicaments.remove(supplierStream
                    .get()
                    .findFirst()
                    .get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void create(Medicament entity) {
        List<Medicament> medicaments = storage.getItemList();
        medicaments.add(entity);
    }
}