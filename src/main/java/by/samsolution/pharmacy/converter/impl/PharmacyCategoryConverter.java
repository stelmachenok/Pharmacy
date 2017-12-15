package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.EnumConverterInterface;
import by.samsolution.pharmacy.dto.PharmacyCategoryDto;
import by.samsolution.pharmacy.entity.PharmacyCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class PharmacyCategoryConverter implements EnumConverterInterface<PharmacyCategoryDto, PharmacyCategory> {

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;

    @Override
    public PharmacyCategoryDto entityToDto(PharmacyCategory pharmacyCategory) {
        PharmacyCategoryDto dto = new PharmacyCategoryDto();
        dto.setFieldName(pharmacyCategory.getFieldName());
        dto.setTranslatedName(message.getMessage(dto.getResPath(), null, LocaleContextHolder.getLocale()));
        return dto;
    }
}
