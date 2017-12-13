package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.EnumConverterInterface;
import by.samsolution.pharmacy.dto.PackingFormDto;
import by.samsolution.pharmacy.entity.PackingForm;

public class PackingFormConverter implements EnumConverterInterface<PackingFormDto, PackingForm> {

    @Override
    public PackingFormDto entityToDto(PackingForm packingForm) {
        PackingFormDto dto = new PackingFormDto();
        dto.setFieldName(packingForm.getFieldName());
        return dto;
    }
}
