package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.storage.Storage;

import java.util.Collections;
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
        return medicaments.stream().filter(m -> m.getGUID().equals(id)).findAny().orElse(null);
    }

    @Override
    public Medicament getEntityByName(String name) {
        List<Medicament> medicaments = storage.getItemList();
        return medicaments.stream().filter(m -> m.getBrandName().equals(name)).findAny().orElse(null);
    }

    @Override
    public boolean update(Medicament entity) {
        List<Medicament> medicaments = storage.getItemList();
        Medicament existedMedicament = medicaments.stream().filter(m -> m.getBrandName().equals(entity.getBrandName())).findAny().orElse(null);
        if (existedMedicament != null) {
            medicaments.remove(existedMedicament);
            medicaments.add(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String name) {
        List<Medicament> medicaments = storage.getItemList();
        Medicament existedMedicament = medicaments.stream().filter(m -> m.getBrandName().equals(name)).findAny().orElse(null);
        if (existedMedicament != null) {
            medicaments.remove(existedMedicament);
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
