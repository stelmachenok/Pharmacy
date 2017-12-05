package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.service.MedicamentService;

import java.util.List;
import java.util.stream.Collectors;

public class MedicamentServiceImpl implements MedicamentService {
    private InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest> medicamentDAO;
    private MedicineConverter medicineConverter;

    public MedicamentServiceImpl(InterfaceDAO<MedicamentEntity, Long, String, MedicamentsSearchRequest> medicamentDAO,
                                 MedicineConverter medicineConverter) {
        this.medicamentDAO = medicamentDAO;
        this.medicineConverter = medicineConverter;
    }

    @Override
    public void add(MedicamentDto medicamentDto) throws ObjectValidationFailedException, EntityAlreadyExistException, JdbcManipulationException {
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        MedicamentEntity entity = medicineConverter.dtoToEntity(medicamentDto);
        List<MedicamentEntity> existedMedicamentEntities = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        if (!equalsMedicaments(existedMedicamentEntities, entity)) {
            medicamentDAO.create(entity);
        } else {
            throw new EntityAlreadyExistException("MedicamentEntity " + entity + " already exist!");
        }
    }

    @Override
    public void update(MedicamentDto medicamentDto) throws ObjectValidationFailedException, EntityNotFoundException, JdbcManipulationException {
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        MedicamentEntity entity = medicineConverter.dtoToEntity(medicamentDto);
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityById(medicamentDto.getId());
        if (existedMedicamentEntity != null) {
            medicamentDAO.update(entity);
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentDto + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {
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
        return medicamentDAO.getAll(request).size();
    }

    @Override
    public int countOf() {
        return medicamentDAO.getAll().size();
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
        return category != null && category2 != null &&
                category.getDescription().equals(category2.getDescription()) &&
                category.getCategoryName().equals(category2.getCategoryName());
    }
}
