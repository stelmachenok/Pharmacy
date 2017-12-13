package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.ComparatorChooser;
import by.samsolution.pharmacy.comparator.medicament.*;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest> {
    private Storage<MedicamentEntity> storage;
    private Long ID;

    public MedicamentDAO() {
        storage = new Storage<>();
        ID = 0L;
    }

    public void setStorage(Storage<MedicamentEntity> storage) {
        this.storage = storage;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public List<MedicamentEntity> getAll() {
        return storage.getItemList();
    }

    @Override
    public List<MedicamentEntity> getAll(MedicamentsSearchRequest request) {
        List<MedicamentEntity> medicaments = getAll();
        List<MedicamentEntity> wantedMedicaments = new ArrayList<>();
        ComparatorChooser chooser = new ComparatorChooser();
        medicaments = (List<MedicamentEntity>) medicaments.stream().
                sorted(chooser.choose(request.getSortField())).
                collect(Collectors.toList());
        if (!request.getDirection()){
            Collections.reverse(medicaments);
        }
        int from = request.getFrom();
        int size = request.getSize();
        int count = countOf();
        int last = count < from + size ? count : from + size;

        for (int i = from; i <= last; i++) {
            wantedMedicaments.add(medicaments.get(i));
        }
        return wantedMedicaments;
    }

    @Override
    public MedicamentEntity getEntityById(Long id) {
        List<MedicamentEntity> medicamentEntities = storage.getItemList();
        return medicamentEntities.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<MedicamentEntity> getEntityByName(String name) {
        List<MedicamentEntity> medicamentEntities = storage.getItemList();
        return medicamentEntities.stream().filter(m -> m.getBrandName().equals(name)).
        collect(Collectors.toList());
    }

    @Override
    public int countOf() {
        return getAll().size();
    }

    @Override
    public int countOf(MedicamentsSearchRequest request) {
        return 0;
    }

    @Override
    public void update(MedicamentEntity entity) {
        List<MedicamentEntity> medicamentEntities = storage.getItemList();
        MedicamentEntity existedMedicamentEntity = medicamentEntities.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        medicamentEntities.remove(existedMedicamentEntity);
        medicamentEntities.add(entity);
    }

    @Override
    public void delete(Long id) {
        List<MedicamentEntity> medicamentEntities = storage.getItemList();
        MedicamentEntity existedMedicamentEntity = medicamentEntities.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        medicamentEntities.remove(existedMedicamentEntity);
    }

    @Override
    public void create(MedicamentEntity entity) {
        List<MedicamentEntity> medicamentEntities = storage.getItemList();
        entity.setId(ID);
        ID++;
        medicamentEntities.add(entity);
    }
}
