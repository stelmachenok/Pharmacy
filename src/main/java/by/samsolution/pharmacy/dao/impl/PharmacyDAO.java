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
public class PharmacyDAO implements InterfaceDAO<Pharmacy, UUID, String> {
    private Storage<Pharmacy> storage;

    public PharmacyDAO() {
        this.storage = new Storage<>();
    }

    @Override
    public List<Pharmacy> getAll() {
        return storage.getItemList();
    }

    @Override
    public Pharmacy getEntityById(UUID id) {
        List<Pharmacy> pharmacies = storage.getItemList();
        return pharmacies.stream().filter(m -> m.getID().equals(id)).findAny().orElse(null);
    }

    @Override
    public Pharmacy getEntityByName(String name) {
        List<Pharmacy> pharmacies = storage.getItemList();
        return pharmacies.stream().filter(m -> m.getPharmacyName().equals(name)).findAny().orElse(null);
    }

    @Override
    public boolean update(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();
        Pharmacy existedPharmacy = pharmacies.stream().filter(m -> m.getPharmacyName().equals(entity.getPharmacyName())).findAny().orElse(null);
        if (existedPharmacy != null) {
            pharmacies.remove(existedPharmacy);
            pharmacies.add(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String name) {
        List<Pharmacy> pharmacies = storage.getItemList();
        Pharmacy existedPharmacy = pharmacies.stream().filter(m -> m.getPharmacyName().equals(name)).findAny().orElse(null);
        if (existedPharmacy != null) {
            pharmacies.remove(existedPharmacy);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void create(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();

        pharmacies.add(entity);

    }
}
