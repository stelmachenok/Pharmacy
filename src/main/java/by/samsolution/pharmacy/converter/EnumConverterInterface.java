package by.samsolution.pharmacy.converter;

import by.samsolution.pharmacy.dto.BasicDto;

public interface EnumConverterInterface<Dto extends BasicDto, Entity> {
    Dto entityToDto(Entity entity);
}
