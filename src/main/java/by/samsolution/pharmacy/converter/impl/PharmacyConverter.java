package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.InterfaceConverter;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.entity.Pharmacy;

public class PharmacyConverter implements InterfaceConverter<PharmacyDto, Pharmacy>{
    @Override
    public PharmacyDto entityToDto(Pharmacy entity) {
        PharmacyDto dto = new PharmacyDto();
        dto.setPharmacyName(entity.getPharmacyName());
        dto.setAddress(entity.getAddress());
        dto.setPharmacistName(entity.getPharmacistName());
        dto.setContactNumber(entity.getContactNumber());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setCategory(entity.getCategory());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public Pharmacy dtoToEntity(PharmacyDto dto) {
        Pharmacy entity = new Pharmacy();
        entity.setPharmacyName(dto.getPharmacyName());
        entity.setAddress(dto.getAddress());
        entity.setPharmacistName(dto.getPharmacistName());
        entity.setContactNumber(dto.getContactNumber());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setCategory(dto.getCategory());
        entity.setId(dto.getId());
        return entity;
    }
}
