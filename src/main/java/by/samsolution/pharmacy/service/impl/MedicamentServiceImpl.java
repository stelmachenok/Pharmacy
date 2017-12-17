package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.service.MedicamentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class MedicamentServiceImpl implements MedicamentService {
    private InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest> medicamentDAO;
    private MedicineConverter medicineConverter;

    public MedicamentServiceImpl(InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest> medicamentDAO,
                                 MedicineConverter medicineConverter) {
        this.medicamentDAO = medicamentDAO;
        this.medicineConverter = medicineConverter;
    }

    @Override
    public void add(MedicamentDto medicamentDto) throws EntityAlreadyExistException, DuplicatePrimaryKeyException {
        MedicamentEntity entity = medicineConverter.dtoToEntity(medicamentDto);
        List<MedicamentEntity> existedMedicamentEntities = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        if (!equalsMedicaments(existedMedicamentEntities, entity)) {
            medicamentDAO.create(entity);
        } else {
            throw new EntityAlreadyExistException("MedicamentEntity " + entity + " already exist!");
        }
    }

    @Override
    public void update(MedicamentDto medicamentDto) throws EntityNotFoundException, EntityAlreadyExistException {
        MedicamentEntity entity = medicineConverter.dtoToEntity(medicamentDto);
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityById(medicamentDto.getId());
        List<MedicamentEntity> existedMedicaments = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        if (equalsMedicaments(existedMedicaments, entity)) {
            throw new EntityAlreadyExistException("MedicamentEntity with same characteristics already exist");
        }
        if (existedMedicamentEntity != null) {
            medicamentDAO.update(entity);
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentDto + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityById(id);
        if (existedMedicamentEntity != null) {
            medicamentDAO.delete(id);
        } else {
            throw new EntityNotFoundException("MedicamentEntity with id " + id + " doesn't exist");
        }
    }

    @Override
    public List<MedicamentDto> getAll() {
        return medicamentDAO.getAll().stream()
                .map((m) -> medicineConverter.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicamentDto> getAll(MedicamentsSearchRequest request) {
        return medicamentDAO.getAll(request).stream()
                .map((m) -> medicineConverter.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentDto getById(Long id) {
        return medicineConverter.entityToDto(medicamentDAO.getEntityById(id));
    }

    @Override
    public int countOf(MedicamentsSearchRequest request) {
        return medicamentDAO.countOf(request);
    }

    @Override
    public int countOf() {
        return medicamentDAO.countOf();
    }

    private boolean equalsMedicaments(List<MedicamentEntity> medicamentEntities, MedicamentEntity medicamentEntity) {
        if (medicamentEntities != null && medicamentEntity != null) {
            for (MedicamentEntity e : medicamentEntities) {
                if (e.getBrandName().equals(medicamentEntity.getBrandName()) &&
                        e.getActiveIngredient().equals(medicamentEntity.getActiveIngredient()) &&
                        e.getDosage().equals(medicamentEntity.getDosage()) &&
                        e.getPackingForm().equals(medicamentEntity.getPackingForm()) &&
                        e.getInternationalNonproprietaryName().equals(medicamentEntity.getInternationalNonproprietaryName()) &&
                        e.getReleaseForm().equals(medicamentEntity.getReleaseForm()) &&
                        equalsCategories(e.getCategory(), medicamentEntity.getCategory()))
                    return true;
            }
        }
        return false;
    }

    private boolean equalsCategories(MedicamentCategory category, MedicamentCategory category2) {
        return category == null && category2 == null ||
                category != null && category2 != null &&
                /*category.getDescription().equals(category2.getDescription()) &&*/
                /*category.getDescription().equals(category2.getDescription()) &&*/
                        category.getCategoryName().equals(category2.getCategoryName());
    }
}
