package DAO.impl;

import DAO.InterfaceDAO;
import entity.MedicamentCategory;
import entity.Pharmacy;
import storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class PharmavyDAO implements InterfaceDAO<Pharmacy, Integer> {
    private Storage<Pharmacy> storage;

    public PharmavyDAO(Storage<Pharmacy> storage) {
        this.storage = storage;
    }

    @Override
    public List<Pharmacy> getAll() {
        return storage.getItemList();
    }

    @Override
    public Pharmacy getEntityById(Integer id) {
        List<Pharmacy> pharmacies = storage.getItemList();
        for (Pharmacy pharmacy : pharmacies) {
            if (pharmacy.getID() == id){
                return pharmacy;
            }
        }
        return null;
    }

    @Override
    public void update(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();
        for (int i = 0; i < pharmacies.size(); i++) {
            Pharmacy pharmacy = pharmacies.get(i);
            if (pharmacy.getID() == entity.getID()) {
                pharmacies.remove(i);
                pharmacies.add(entity);
                break;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        List<Pharmacy> pharmacies = storage.getItemList();
        for (int i = 0; i < pharmacies.size(); i++) {
            Pharmacy pharmacy = pharmacies.get(i);
            if (pharmacy.getID() == id) {
                pharmacies.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean create(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();
        for (Pharmacy pharmacy : pharmacies) {
            if (pharmacy.getPharmacyName().equals(entity.getPharmacyName()) &&
                    pharmacy.getAddress().equals(entity.getAddress()) &&
                    pharmacy.getID() == entity.getID()){
                return false;
            }
        }
        pharmacies.add(entity);
        return true;
    }
}
