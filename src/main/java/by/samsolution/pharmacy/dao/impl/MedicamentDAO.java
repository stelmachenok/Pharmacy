package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<MedicamentDto, Long, String> {
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
