package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.comparator.MedicamentBrandNameComparator;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.MedicamentsSearchRequest;
import by.samsolution.pharmacy.service.MedicamentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MedicamentServiceImpl implements MedicamentService{
    private MedicamentDAO medicamentDAO;

    public MedicamentServiceImpl(MedicamentDAO medicamentDAO) {
        this.medicamentDAO = medicamentDAO;
    }

    @Override
    public void add(MedicamentDto medicamentDto) throws ObjectValidationFailedException, EntityAlreadyExistException {
        MedicamentDto existedMedicamentDto = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        if (!equalsMedicaments(existedMedicamentDto, medicamentDto)) {
            medicamentDAO.create(medicamentDto);
        } else {
            throw new EntityAlreadyExistException("MedicamentEntity " + existedMedicamentDto + " already exist!");
        }
    }

    @Override
    public void update(MedicamentDto medicamentDto) throws ObjectValidationFailedException, EntityNotFoundException {
        MedicamentDto existedMedicamentDto = medicamentDAO.getEntityById(medicamentDto.getId());
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        if (existedMedicamentDto != null) {
            medicamentDAO.update(medicamentDto);
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentDto + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        MedicamentDto existedMedicamentDto = medicamentDAO.getEntityById(id);
        if (existedMedicamentDto != null) {
            medicamentDAO.delete(id);
        } else {
            throw new EntityNotFoundException("MedicamentEntity with id " + id + " doesn't exist");
        }
    }

    @Override
    public List<MedicamentDto> getAll() {
        return medicamentDAO.getAll();
    }

    @Override
    public List<MedicamentDto> getAll(MedicamentsSearchRequest request) {
        int from = request.getFrom();
        int size = request.getSize();
        List<MedicamentDto> medicaments = medicamentDAO.getAll();
        int count = countOf();
        int last = count < from + size ? count : from + size;
        List<MedicamentDto> wantedMedicaments = new ArrayList<>();
        for (int i = from; i <= last; i++){
            wantedMedicaments.add(medicaments.get(i));
        }
        if (request.getSortField() != null){
            return wantedMedicaments.stream().
                    sorted(new MedicamentBrandNameComparator()).
                    collect(Collectors.toList());
        }
        return wantedMedicaments;
    }

    @Override
    public int countOf(MedicamentsSearchRequest request) {
        return medicamentDAO.getAll().size();
    }

    @Override
    public int countOf() {
        return medicamentDAO.getAll().size();
    }

    private boolean equalsMedicaments(MedicamentDto medicamentEntity, MedicamentDto medicamentEntity2) {
        return medicamentEntity != null && medicamentEntity2 != null &&
                medicamentEntity.getBrandName().equals(medicamentEntity2.getBrandName()) &&
                medicamentEntity.getActiveIngredient().equals(medicamentEntity2.getActiveIngredient()) &&
                medicamentEntity.getDosage().equals(medicamentEntity2.getDosage()) &&
                medicamentEntity.getPackingForm().equals(medicamentEntity2.getPackingForm()) &&
                medicamentEntity.getInternationalNonproprietaryName().equals(medicamentEntity2.getInternationalNonproprietaryName()) &&
                medicamentEntity.getReleaseForm().equals(medicamentEntity2.getReleaseForm());
    }
}
