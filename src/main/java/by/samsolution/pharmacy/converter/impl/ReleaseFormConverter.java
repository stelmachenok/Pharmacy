package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.EnumConverterInterface;
import by.samsolution.pharmacy.dto.ReleaseFormDto;
import by.samsolution.pharmacy.entity.ReleaseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

public class ReleaseFormConverter implements EnumConverterInterface<ReleaseFormDto, ReleaseForm> {

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource message;
    
    @Override
    public ReleaseFormDto entityToDto(ReleaseForm releaseForm) {
        ReleaseFormDto dto = new ReleaseFormDto();
        dto.setFieldName(releaseForm.getFieldName());
        dto.setTranslatedName(message.getMessage(dto.getResPath(), null, null));
        return dto;
    }
}
