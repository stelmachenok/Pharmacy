package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.PharmacyConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyJdbcDao;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.PharmacySearchRequest;
import by.samsolution.pharmacy.service.PharmacyService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
public class PharmacyServiceImpl implements PharmacyService {
    private InterfaceDAO<Pharmacy, Long, String, PharmacySearchRequest> pharmacyDAO;
    private PharmacyConverter pharmacyConverter;

    public PharmacyServiceImpl(InterfaceDAO<Pharmacy, Long, String, PharmacySearchRequest> pharmacyDAO, PharmacyConverter pharmacyConverter) {
        this.pharmacyDAO = pharmacyDAO;
        this.pharmacyConverter = pharmacyConverter;
    }

    @Override
    public void add(PharmacyDto dto) throws ObjectValidationFailedException, EntityAlreadyExistException, JdbcManipulationException {
        Pharmacy entity = pharmacyConverter.dtoToEntity(dto);
        List<Pharmacy> existedPharmacyEntities = pharmacyDAO.getEntityByName(dto.getPharmacyName());
        if (!equalsPharmacies(existedPharmacyEntities, entity)) {
            pharmacyDAO.create(entity);
        } else {
            throw new EntityAlreadyExistException("Pharmacy " + entity + " already exist!");
        }
    }

    @Override
    public void update(PharmacyDto dto) throws ObjectValidationFailedException, EntityNotFoundException, EntityAlreadyExistException, JdbcManipulationException {

        Pharmacy entity = pharmacyConverter.dtoToEntity(dto);
        Pharmacy existedPharmacyEntity = pharmacyDAO.getEntityById(dto.getId());
        List<Pharmacy> existedPharmacies = pharmacyDAO.getEntityByName(dto.getPharmacyName());
        if (equalsPharmacies(existedPharmacies, entity)) {
            throw new EntityAlreadyExistException("Pharmacy with same characteristics already exist");
        }
        if (existedPharmacyEntity != null) {
            pharmacyDAO.update(entity);
        } else {
            throw new EntityNotFoundException("Pharmacy " + dto + " doesn't exist");
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {
        Pharmacy existedMedicamentEntity = pharmacyDAO.getEntityById(id);
        if (existedMedicamentEntity != null) {
            pharmacyDAO.delete(id);
        } else {
            throw new EntityNotFoundException("Pharmacy with id " + id + " doesn't exist");
        }
    }

    @Override
    public void delete(PharmacySearchRequest request) throws EntityNotFoundException, JdbcManipulationException {

    }

    @Override
    public List<PharmacyDto> getAll() {
        return pharmacyDAO.getAll().stream()
                .map((m) -> pharmacyConverter.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacyDto> getAll(PharmacySearchRequest request) {
        return pharmacyDAO.getAll(request).stream()
                .map((m) -> pharmacyConverter.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyDto getById(Long id) {
        return pharmacyConverter.entityToDto(pharmacyDAO.getEntityById(id));
    }

    @Override
    public int countOf(PharmacySearchRequest request) {
        return pharmacyDAO.getAll(request).size();
    }

    @Override
    public int countOf() {
        return pharmacyDAO.getAll().size();
    }

    private boolean equalsPharmacies(List<Pharmacy> pharmacyEntities, Pharmacy pharmacy) {
        if (pharmacyEntities != null && pharmacy != null) {
            for (Pharmacy p : pharmacyEntities) {
                if (p.getPharmacyName().equals(pharmacy.getPharmacyName()) &&
                        p.getAddress().equals(pharmacy.getAddress()) &&
                        p.getPharmacistName().equals(pharmacy.getPharmacistName()) &&
                        p.getContactNumber().equals(pharmacy.getContactNumber()) &&
                        p.getCategory().equals(pharmacy.getCategory()))
                    return true;
            }
        }
        return false;
    }
}
