package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class PharmacyDAO implements InterfaceDAO<Pharmacy, UUID> {
    private Storage<Pharmacy> storage;

    public PharmacyDAO(Storage<Pharmacy> storage) {
        this.storage = storage;
    }

    @Override
    public List<Pharmacy> getAll() {
        return storage.getItemList();
    }

    @Override
    public Pharmacy getEntityById(UUID id) {
        List<Pharmacy> pharmacies = storage.getItemList();
        Supplier<Stream<Pharmacy>> supplierStream =
                () -> pharmacies
                        .stream()
                        .filter((p) -> p.getID().equals(id));
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
    public void update(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();
        Supplier<Stream<Pharmacy>> supplierStream =
                () -> pharmacies
                        .stream()
                        .filter((p) -> p.getID().equals(entity.getID()));
        if (supplierStream.get().anyMatch(s -> true)) {
            pharmacies.remove(supplierStream
                    .get()
                    .findFirst()
                    .get());
            pharmacies.add(entity);
        }
    }

    @Override
    public boolean delete(UUID id) {
        List<Pharmacy> pharmacies = storage.getItemList();
        Supplier<Stream<Pharmacy>> supplierStream =
                () -> pharmacies
                        .stream()
                        .filter((p) -> p.getID().equals(id));
        if (supplierStream.get().anyMatch(s -> true)) {
            pharmacies.remove(supplierStream
                    .get()
                    .findFirst()
                    .get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean create(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();
        Supplier<Stream<Pharmacy>> supplierStream =
                () -> pharmacies
                        .stream()
                        .filter((p) -> p.getPharmacyName().equals(entity.getPharmacistName()) &&
                        p.getAddress().equals(entity.getAddress()));
        if (supplierStream.get().noneMatch(s -> true)){
            pharmacies.add(entity);
            return true;
        }
        else{
            return false;
        }
    }
}
