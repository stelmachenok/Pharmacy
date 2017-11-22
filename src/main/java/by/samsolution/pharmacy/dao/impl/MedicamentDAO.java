package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.comparator.medicament.*;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<MedicamentDto, Long, String, MedicamentsSearchRequest> {
    private Storage<MedicamentDto> storage;
    private Long ID;

    public MedicamentDAO() {
        storage = new Storage<>();
        ID = 0L;
    }

    public void setStorage(Storage<MedicamentDto> storage) {
        this.storage = storage;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public List<MedicamentDto> getAll() {
        return storage.getItemList();
    }

    @Override
    public List<MedicamentDto> getAll(MedicamentsSearchRequest request) {
        int from = request.getFrom();
        int size = request.getSize();
        List<MedicamentDto> medicaments = getAll();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        List<MedicamentDto> wantedMedicaments = new ArrayList<>();
        for (int i = from; i <= last; i++){
            wantedMedicaments.add(medicaments.get(i));
        }
        if (request.getSortField() != null){
            switch (request.getSortField()){
                case BRAND_NAME:{
                    return wantedMedicaments.stream().
                            sorted(new MedicamentBrandNameComparator()).
                            collect(Collectors.toList());
                }
                case ACTIVE_INGREDIENT:{
                    return wantedMedicaments.stream().
                            sorted(new MedicamentActiveIngredientComparator()).
                            collect(Collectors.toList());
                }
                case DOSAGE:{
                    return wantedMedicaments.stream().
                            sorted(new MedicamentDosageComparator()).
                            collect(Collectors.toList());
                }
                case PACKING_FORM:{
                    return wantedMedicaments.stream().
                            sorted(new MedicamentPackingFormComparator()).
                            collect(Collectors.toList());
                }
                case INTERNATIONAL_NONPROPRIENTARY_NAME:{
                    return wantedMedicaments.stream().
                            sorted(new MedicamentInternationalNonproprietaryNameComparator()).
                            collect(Collectors.toList());
                }
                case RELEASE_FORM:{
                    return wantedMedicaments.stream().
                            sorted(new MedicamentReleaseFormComparator()).
                            collect(Collectors.toList());
                }
            }
        }
        return wantedMedicaments;
    }

    @Override
    public MedicamentDto getEntityById(Long id) {
        List<MedicamentDto> medicamentEntities = storage.getItemList();
        return medicamentEntities.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public MedicamentDto getEntityByName(String name) {
        List<MedicamentDto> medicamentEntities = storage.getItemList();
        return medicamentEntities.stream().filter(m -> m.getBrandName().equals(name)).findAny().orElse(null);
    }

    @Override
    public int countOf() {
        return getAll().size();
    }

    @Override
    public void update(MedicamentDto entity){
        List<MedicamentDto> medicamentEntities = storage.getItemList();
        MedicamentDto existedMedicamentDto = medicamentEntities.stream().filter(m -> m.getId().equals(entity.getId())).findAny().orElse(null);
        medicamentEntities.remove(existedMedicamentDto);
        medicamentEntities.add(entity);
    }

    @Override
    public void delete(Long id) {
        List<MedicamentDto> medicamentEntities = storage.getItemList();
        MedicamentDto existedMedicamentDto = medicamentEntities.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
        medicamentEntities.remove(existedMedicamentDto);
    }

    @Override
    public void create(MedicamentDto entity) {
        List<MedicamentDto> medicamentEntities = storage.getItemList();
        entity.setId(ID);
        ID++;
        medicamentEntities.add(entity);
    }
}
