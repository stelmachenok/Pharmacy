package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.InterfaceConverter;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentEntity;

public class MedicineConverter implements InterfaceConverter<MedicamentDto, MedicamentEntity> {

    @Override
    public MedicamentDto entityToDto(MedicamentEntity entity) {
        MedicamentDto dto = new MedicamentDto();
        dto.setBrandName(entity.getBrandName());
        dto.setActiveIngredient(entity.getActiveIngredient());
        dto.setDosage(entity.getDosage());
        dto.setPackingForm(entity.getPackingForm());
        dto.setInternationalNonproprietaryName(entity.getInternationalNonproprietaryName());
        dto.setReleaseForm(entity.getReleaseForm());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public MedicamentEntity dtoToEntity(MedicamentDto dto) {
        MedicamentEntity entity = new MedicamentEntity();
        entity.setBrandName(dto.getBrandName());
        entity.setActiveIngredient(dto.getActiveIngredient());
        entity.setDosage(dto.getDosage());
        entity.setPackingForm(dto.getPackingForm());
        entity.setInternationalNonproprietaryName(dto.getInternationalNonproprietaryName());
        entity.setReleaseForm(dto.getReleaseForm());
        entity.setId(dto.getId());
        return entity;
    }
}
