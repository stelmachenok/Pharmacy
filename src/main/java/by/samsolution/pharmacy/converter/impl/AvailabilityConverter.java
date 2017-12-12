package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.InterfaceConverter;
import by.samsolution.pharmacy.dto.AvailabilityDto;
import by.samsolution.pharmacy.entity.AvailabilityEntity;

public class AvailabilityConverter implements InterfaceConverter<AvailabilityDto, AvailabilityEntity> {
    @Override
    public AvailabilityDto entityToDto(AvailabilityEntity entity) {
        AvailabilityDto dto = new AvailabilityDto();
        dto.setPharmacyId(entity.getPharmacyId());
        dto.setMedicamentId(entity.getMedicamentId());
        dto.setCount(entity.getCount());
        dto.setLastUpdate(entity.getLastUpdate());
        return dto;
    }

    @Override
    public AvailabilityEntity dtoToEntity(AvailabilityDto dto) {
        AvailabilityEntity entity = new AvailabilityEntity();
        entity.setPharmacyId(dto.getPharmacyId());
        entity.setMedicamentId(dto.getMedicamentId());
        entity.setCount(dto.getCount());
        entity.setLastUpdate(dto.getLastUpdate());
        return entity;
    }
}
