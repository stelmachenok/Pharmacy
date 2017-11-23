package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.PharmacyConverter;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import by.samsolution.pharmacy.service.PharmacyService;

import java.util.List;

public class PharmacyServiceImpl implements PharmacyService {
    private PharmacyDAO pharmacyDAO;
    private PharmacyConverter pharmacyConverter;

    public PharmacyServiceImpl(PharmacyDAO pharmacyDAO, PharmacyConverter pharmacyConverter) {
        this.pharmacyDAO = pharmacyDAO;
        this.pharmacyConverter = pharmacyConverter;
    }

    @Override
    public void add(PharmacyDto dto) throws ObjectValidationFailedException, EntityAlreadyExistException {

    }

    @Override
    public void update(PharmacyDto dto) throws ObjectValidationFailedException, EntityNotFoundException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {

    }

    @Override
    public List<PharmacyDto> getAll() {
        return null;
    }

    @Override
    public List<PharmacyDto> getAll(PharmacySearchRequest request) {
        return null;
    }

    @Override
    public PharmacyDto getById(Long id) {
        return pharmacyConverter.entityToDto(pharmacyDAO.getEntityById(id));
    }

    @Override
    public int countOf(PharmacySearchRequest request) {
        return 0;
    }

    @Override
    public int countOf() {
        return 0;
    }
}
