package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.AvailabilityConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.AvailabilityDto;
import by.samsolution.pharmacy.entity.AvailabilityEntity;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.AvailabilitySearchRequest;
import by.samsolution.pharmacy.service.AvailabilityService;

import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityServiceImpl implements AvailabilityService {
    private InterfaceDAO<AvailabilityEntity, Long, String, AvailabilitySearchRequest> availabilityDAO;
    private AvailabilityConverter availabilityConverter;

    public AvailabilityServiceImpl(InterfaceDAO<AvailabilityEntity, Long, String, AvailabilitySearchRequest> availabilityDAO, AvailabilityConverter availabilityConverter) {
        this.availabilityDAO = availabilityDAO;
        this.availabilityConverter = availabilityConverter;
    }

    @Override
    public void add(AvailabilityDto dto) throws ObjectValidationFailedException, EntityAlreadyExistException, JdbcManipulationException, EntityNotFoundException {
        AvailabilityEntity entity = availabilityConverter.dtoToEntity(dto);
        AvailabilityEntity existedMedicamentEntities = availabilityDAO.getEntityById(dto.getPharmacyId());
        if (!equalsAvailabilities(existedMedicamentEntities, entity)) {
            availabilityDAO.create(entity);
        } else {
            availabilityDAO.update(entity);
        }
    }

    @Override
    public void update(AvailabilityDto dto) throws ObjectValidationFailedException, EntityNotFoundException, JdbcManipulationException, EntityAlreadyExistException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, JdbcManipulationException {

    }

    @Override
    public List<AvailabilityDto> getAll() {
        return null;
    }

    @Override
    public List<AvailabilityDto> getAll(AvailabilitySearchRequest request) {
        return availabilityDAO.getAll(request).stream()
                .map((m) -> availabilityConverter.entityToDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public AvailabilityDto getById(Long id) {
        return null;
    }

    @Override
    public int countOf(AvailabilitySearchRequest request) {
        return 0;
    }

    @Override
    public int countOf() {
        return 0;
    }

    private boolean equalsAvailabilities(AvailabilityEntity entity, AvailabilityEntity entity2){
        return entity != null && entity2 != null &&
                entity.getPharmacyId().equals(entity2.getPharmacyId()) &&
                entity.getMedicamentId().equals(entity2.getMedicamentId());
    }
}
