package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.pharmacy.*;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
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
        int from = request.getFrom();
        int size = request.getSize();
        List<Pharmacy> pharmacies = getAll();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        List<Pharmacy> wantedPharmacy = new ArrayList<>();
        for (int i = from; i <= last; i++) {
            wantedPharmacy.add(pharmacies.get(i));
        }
        if (request.getSortField() != null) {
            switch (request.getSortField()) {
                case PHARMACY_NAME: {
                    return wantedPharmacy.stream().
                            sorted(new PharmacyNameComparator()).
                            collect(Collectors.toList());
                }
                case ADDRESS: {
                    return wantedPharmacy.stream().
                            sorted(new PharmacyAddressComparator()).
                            collect(Collectors.toList());
                }
                case PHARMACIST_NAME: {
                    return wantedPharmacy.stream().
                            sorted(new PharmacyPharmacistNameComparator()).
                            collect(Collectors.toList());
                }
                case CONTACT_NUMBER: {
                    return wantedPharmacy.stream().
                            sorted(new PharmacyContactNumberComparator()).
                            collect(Collectors.toList());
                }
                case CATEGORY: {
                    return wantedPharmacy.stream().
                            sorted(new PharmacyCategoryComparator()).
                            collect(Collectors.toList());
                }
            }
        }
        return wantedPharmacy;
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
