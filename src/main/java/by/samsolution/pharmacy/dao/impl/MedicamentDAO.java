package by.samsolution.pharmacy.dao.impl;

import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.storage.Storage;

import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentDAO implements InterfaceDAO<MedicamentEntity, Long, String> {
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
    public void update(MedicamentEntity entity){
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
