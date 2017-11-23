package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.service.MedicamentService;

import java.util.List;
import java.util.stream.Collectors;

public class MedicamentServiceImpl implements MedicamentService {
    private MedicamentDAO medicamentDAO;
    private MedicineConverter medicineConverter;

    public MedicamentServiceImpl(MedicamentDAO medicamentDAO, MedicineConverter medicineConverter) {
        this.medicamentDAO = medicamentDAO;
        this.medicineConverter = medicineConverter;
    }

    @Override
    public void add(MedicamentDto medicamentDto) throws ObjectValidationFailedException, EntityAlreadyExistException {
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        MedicamentEntity entity = medicineConverter.dtoToEntity(medicamentDto);
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        if (!equalsMedicaments(existedMedicamentEntity, entity)) {

            medicamentDAO.create(entity);
        } else {
            throw new EntityAlreadyExistException("MedicamentEntity " + existedMedicamentEntity + " already exist!");
        }
    }

    @Override
    public void update(MedicamentDto medicamentDto) throws ObjectValidationFailedException, EntityNotFoundException {
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
        return medicamentDAO.getAll().size();
    }

    @Override
    public int countOf() {
        return medicamentDAO.getAll().size();
    }

    private boolean equalsMedicaments(MedicamentEntity medicamentEntity, MedicamentEntity medicamentEntity2) {
        return medicamentEntity != null && medicamentEntity2 != null &&
                medicamentEntity.getBrandName().equals(medicamentEntity2.getBrandName()) &&
                medicamentEntity.getActiveIngredient().equals(medicamentEntity2.getActiveIngredient()) &&
                medicamentEntity.getDosage().equals(medicamentEntity2.getDosage()) &&
                medicamentEntity.getPackingForm().equals(medicamentEntity2.getPackingForm()) &&
                medicamentEntity.getInternationalNonproprietaryName().equals(medicamentEntity2.getInternationalNonproprietaryName()) &&
                medicamentEntity.getReleaseForm().equals(medicamentEntity2.getReleaseForm());
    }
}
