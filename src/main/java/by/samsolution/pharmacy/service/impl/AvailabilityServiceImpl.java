package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.AvailabilityConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dao.impl.AvailabilityJdbcDao;
import by.samsolution.pharmacy.dto.AvailabilityDto;
import by.samsolution.pharmacy.entity.AvailabilityEntity;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.JdbcManipulationException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.searchrequest.impl.AvailabilitySearchRequest;
import by.samsolution.pharmacy.service.AvailabilityService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
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
        AvailabilityEntity existedAvailabilityEntities = availabilityDAO.getEntityById(dto.getPharmacyId());
        if (!equalsAvailabilities(existedAvailabilityEntities, entity)) {
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
        AvailabilityEntity existedAvailabilityEntity = availabilityDAO.getEntityById(id);
        if (existedAvailabilityEntity != null) {
            availabilityDAO.delete(id);
        } else {
            throw new EntityNotFoundException("Availability with id " + id + " doesn't exist");
        }
    }

    @Override
    public void delete(AvailabilitySearchRequest request) throws EntityNotFoundException, JdbcManipulationException {
        List<AvailabilityEntity> existedAvailabilityEntities = availabilityDAO.getAll(request);
        if (existedAvailabilityEntities != null) {
            availabilityDAO.delete(request);
        } else {
            throw new EntityNotFoundException("Availability with id " + request.getMedicamentId() + " doesn't exist");
        }
    }

    @Override
    public List<AvailabilityDto> getAll() {
        return availabilityDAO.getAll().stream()
                .map((m) -> availabilityConverter.entityToDto(m))
                .collect(Collectors.toList());
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
        return availabilityDAO.countOf(request);
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
