package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.EnumConverterInterface;
import by.samsolution.pharmacy.dto.ReleaseFormDto;
import by.samsolution.pharmacy.entity.ReleaseForm;

public class ReleaseFormConverter implements EnumConverterInterface<ReleaseFormDto, ReleaseForm> {
    
    @Override
    public ReleaseFormDto entityToDto(ReleaseForm releaseForm) {
        ReleaseFormDto dto = new ReleaseFormDto();
        dto.setFieldName(releaseForm.getFieldName());
        return dto;
    }
}
