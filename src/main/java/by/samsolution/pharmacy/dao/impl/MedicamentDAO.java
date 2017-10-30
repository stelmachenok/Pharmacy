package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;
import java.util.UUID;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<Medicament, Long, String> {
    private Storage<Medicament> storage;
    private long ID;

    public MedicamentDAO() {
        storage = new Storage<>();
        ID = 0;
    }

    @Override
    public List<Medicament> getAll() {
        return storage.getItemList();
    }

    @Override
    public Medicament getEntityById(Long id) {
        List<Medicament> medicaments = storage.getItemList();
        return medicaments.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Medicament getEntityByName(String name) {
        List<Medicament> medicaments = storage.getItemList();
        return medicaments.stream().filter(m -> m.getBrandName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void update(Medicament entity) throws EntityNotFoundException {
        List<Medicament> medicaments = storage.getItemList();
        Medicament existedMedicament = medicaments.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        if (existedMedicament != null) {
            medicaments.remove(existedMedicament);
            medicaments.add(entity);
        } else {
            throw new EntityNotFoundException("Medicament " + entity + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        List<Medicament> medicaments = storage.getItemList();
        Medicament existedMedicament = medicaments.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        if (existedMedicament != null) {
            medicaments.remove(existedMedicament);
        } else {
            throw new EntityNotFoundException("Medicament with ID = " + id + " doesn't exist");
        }
    }

    @Override
    public void create(Medicament entity) {
        List<Medicament> medicaments = storage.getItemList();
        entity.setId(ID);
        ID++;
        medicaments.add(entity);
    }
}
