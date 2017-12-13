package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.EnumConverterInterface;
import by.samsolution.pharmacy.dto.PharmacyCategoryDto;
import by.samsolution.pharmacy.entity.PharmacyCategory;

public class PharmacyCategoryConverter implements EnumConverterInterface<PharmacyCategoryDto, PharmacyCategory> {

    @Override
    public PharmacyCategoryDto entityToDto(PharmacyCategory pharmacyCategory) {
        PharmacyCategoryDto dto = new PharmacyCategoryDto();
        dto.setFieldName(pharmacyCategory.getFieldName());
        return dto;
    }
}
