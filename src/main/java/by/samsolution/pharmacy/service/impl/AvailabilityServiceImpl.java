package by.samsolution.pharmacy.service.impl;

import by.samsolution.pharmacy.converter.impl.AvailabilityConverter;
import by.samsolution.pharmacy.dao.InterfaceDAO;
import by.samsolution.pharmacy.dto.AvailabilityDto;
import by.samsolution.pharmacy.entity.AvailabilityEntity;
import by.samsolution.pharmacy.exception.DuplicatePrimaryKeyException;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
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
    public void add(AvailabilityDto dto) throws EntityAlreadyExistException, EntityNotFoundException, DuplicatePrimaryKeyException {
        AvailabilityEntity entity = availabilityConverter.dtoToEntity(dto);
        AvailabilitySearchRequest request = new AvailabilitySearchRequest();
        request.setPharmacyId(dto.getPharmacyId());
        List<AvailabilityEntity> existedAvailabilityEntities = availabilityDAO.getAll(request);
        if (!equalsAvailabilities(existedAvailabilityEntities, entity)) {
            availabilityDAO.create(entity);
        } else {
            availabilityDAO.update(entity);
        }
    }

    @Override
    public void update(AvailabilityDto dto) throws EntityNotFoundException, EntityAlreadyExistException {

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        AvailabilityEntity existedAvailabilityEntity = availabilityDAO.getEntityById(id);
        if (existedAvailabilityEntity != null) {
            availabilityDAO.delete(id);
        } else {
            throw new EntityNotFoundException("Availability with id " + id + " doesn't exist");
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
        return availabilityDAO.countOf();
    }

    private boolean equalsAvailabilities(List<AvailabilityEntity> entities, AvailabilityEntity entity) {
        if (entities != null && entity != null) {
            for (AvailabilityEntity a : entities) {
                if (a.getPharmacyId().equals(entity.getPharmacyId()) &&
                        a.getMedicamentId().equals(entity.getMedicamentId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
