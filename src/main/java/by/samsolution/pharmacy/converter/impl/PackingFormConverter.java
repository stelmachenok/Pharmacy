package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.EnumConverterInterface;
import by.samsolution.pharmacy.dto.PackingFormDto;
import by.samsolution.pharmacy.entity.PackingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class PackingFormConverter implements EnumConverterInterface<PackingFormDto, PackingForm> {
    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;


    @Override
    public PackingFormDto entityToDto(PackingForm packingForm) {
        PackingFormDto dto = new PackingFormDto();
        dto.setFieldName(packingForm.getFieldName());
        dto.setTranslatedName(message.getMessage(dto.getResPath(), null, LocaleContextHolder.getLocale()));
        return dto;
    }


}
