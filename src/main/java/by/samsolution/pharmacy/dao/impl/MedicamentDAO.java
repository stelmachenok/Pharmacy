package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.medicament.*;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
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
        switch (request.getSortField()) {
            case BRAND_NAME: {
                medicaments = medicaments.stream().
                        sorted(new MedicamentBrandNameComparator()).
                        collect(Collectors.toList());
                break;
            }
            case ACTIVE_INGREDIENT: {
                medicaments = medicaments.stream().
                        sorted(new MedicamentActiveIngredientComparator()).
                        collect(Collectors.toList());
                break;
            }
            case DOSAGE: {
                medicaments = medicaments.stream().
                        sorted(new MedicamentDosageComparator()).
                        collect(Collectors.toList());
                break;
            }
            case PACKING_FORM: {
                medicaments = medicaments.stream().
                        sorted(new MedicamentPackingFormComparator()).
                        collect(Collectors.toList());
                break;
            }
            case INTERNATIONAL_NONPROPRIENTARY_NAME: {
                medicaments = medicaments.stream().
                        sorted(new MedicamentInternationalNonproprietaryNameComparator()).
                        collect(Collectors.toList());
                break;
            }
            case RELEASE_FORM: {
                medicaments = medicaments.stream().
                        sorted(new MedicamentReleaseFormComparator()).
                        collect(Collectors.toList());
                break;
            }
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
    public MedicamentEntity getEntityByName(String name) {
        List<MedicamentEntity> medicamentEntities = storage.getItemList();
        return medicamentEntities.stream().filter(m -> m.getBrandName().equals(name)).findAny().orElse(null);
    }

    @Override
    public int countOf() {
        return getAll().size();
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
