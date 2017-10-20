package DAO.impl;

import DAO.InterfaceDAO;
import entity.Medicament;
import storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<Medicament, Integer> {
    private Storage<Medicament> storage;

    public MedicamentDAO() {
        storage = new Storage<>();
    }

    @Override
    public List<Medicament> getAll() {
        return storage.getItemList();
    }

    @Override
    public Medicament getEntityById(Integer id) {
        for (Medicament medicament : storage.getItemList()) {
            if (medicament.getGUID() == id) {
                return medicament;
            }
        }
        return null;
    }

    @Override
    public void update(Medicament entity) {
        List<Medicament> medicaments = storage.getItemList();
        for (int i = 0; i < medicaments.size(); i++) {
            Medicament medicament = medicaments.get(i);
            if (medicament.getGUID() == entity.getGUID()) {
                medicaments.remove(i);
                medicaments.add(entity);
                break;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        List<Medicament> medicaments = storage.getItemList();
        for (int i = 0; i < medicaments.size(); i++) {
            Medicament medicament = medicaments.get(i);
            if (medicament.getGUID() == id) {
                medicaments.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean create(Medicament entity) {
        List<Medicament> medicaments = storage.getItemList();
        for (Medicament medicament : medicaments) {
            if (medicament.getBrandName().equals(entity.getBrandName()) &&
                    medicament.getActiveIngredient().equals(entity.getActiveIngredient()) &&
                    medicament.getDosage() == entity.getDosage() &&
                    medicament.getPackingForm().equals(entity.getPackingForm()) &&
                    medicament.getInternationalNonproprietaryName().equals(entity.getInternationalNonproprietaryName()) &&
                    medicament.getGUID() == entity.getGUID()){
                return false;
            }
        }
        medicaments.add(entity);
        return true;
    }
}
