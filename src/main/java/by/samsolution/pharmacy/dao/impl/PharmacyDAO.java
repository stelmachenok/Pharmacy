package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.ComparatorChooser;
import by.samsolution.pharmacy.comparator.pharmacy.*;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class PharmacyDAO implements InterfaceDAO<Pharmacy, Long, String, PharmacySearchRequest> {
    private Storage<Pharmacy> storage;
    private Long ID;

    public PharmacyDAO() {
        this.storage = new Storage<>();
        ID = 0L;
    }

    public void setStorage(Storage<Pharmacy> storage) {
        this.storage = storage;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public List<Pharmacy> getAll() {
        return storage.getItemList();
    }

    @Override
    public List<Pharmacy> getAll(PharmacySearchRequest request) {
        List<Pharmacy> pharmacies = getAll();
        List<Pharmacy> wantedPharmacies = new ArrayList<>();
        ComparatorChooser chooser = new ComparatorChooser();
        pharmacies = (List<Pharmacy>) pharmacies.stream().
                sorted(chooser.choose(request.getSortField())).
                collect(Collectors.toList());
        if (!request.getDirection()){
            Collections.reverse(pharmacies);
        }
        int from = request.getFrom();
        int size = request.getSize();
        int count = countOf();
        int last = count < from + size ? count : from + size;

        for (int i = from; i <= last; i++) {
            wantedPharmacies.add(pharmacies.get(i));
        }
        return wantedPharmacies;
    }

    @Override
    public Pharmacy getEntityById(Long id) {
        List<Pharmacy> pharmacies = storage.getItemList();
        return pharmacies.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Pharmacy getEntityByName(String name) {
        List<Pharmacy> pharmacies = storage.getItemList();
        return pharmacies.stream().filter(m -> m.getPharmacyName().equals(name)).findAny().orElse(null);
    }

    @Override
    public int countOf() {
        return getAll().size();
    }

    @Override
    public void update(Pharmacy entity) throws EntityNotFoundException {
        List<Pharmacy> pharmacies = storage.getItemList();
        Pharmacy existedPharmacy = pharmacies.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        pharmacies.remove(existedPharmacy);
        pharmacies.add(entity);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        List<Pharmacy> pharmacies = storage.getItemList();
        Pharmacy existedPharmacy = pharmacies.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        pharmacies.remove(existedPharmacy);
    }

    @Override
    public void create(Pharmacy entity) {
        List<Pharmacy> pharmacies = storage.getItemList();
        entity.setId(ID);
        ID++;
        pharmacies.add(entity);

    }
}
