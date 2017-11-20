package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 23.10.2017.
 */
public class PharmacyDAO implements InterfaceDAO<PharmacyDto, Long, String> {
    private Storage<PharmacyDto> storage;
    private Long ID;

    public PharmacyDAO() {
        this.storage = new Storage<>();
        ID = 0L;
    }

    public void setStorage(Storage<PharmacyDto> storage) {
        this.storage = storage;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public List<PharmacyDto> getAll() {
        return storage.getItemList();
    }

    @Override
    public PharmacyDto getEntityById(Long id) {
        List<PharmacyDto> pharmacies = storage.getItemList();
        return pharmacies.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public PharmacyDto getEntityByName(String name) {
        List<PharmacyDto> pharmacies = storage.getItemList();
        return pharmacies.stream().filter(m -> m.getPharmacyName().equals(name)).findAny().orElse(null);
    }

    @Override
    public void update(PharmacyDto entity) throws EntityNotFoundException {
        List<PharmacyDto> pharmacies = storage.getItemList();
        PharmacyDto existedPharmacyDto = pharmacies.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        pharmacies.remove(existedPharmacyDto);
        pharmacies.add(entity);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        List<PharmacyDto> pharmacies = storage.getItemList();
        PharmacyDto existedPharmacyDto = pharmacies.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        pharmacies.remove(existedPharmacyDto);
    }

    @Override
    public void create(PharmacyDto entity) {
        List<PharmacyDto> pharmacies = storage.getItemList();
        entity.setId(ID);
        ID++;
        pharmacies.add(entity);

    }
}
