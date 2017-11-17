package by.samsolution.pharmacy.converter;

import by.samsolution.pharmacy.dto.BasicDto;
import by.samsolution.pharmacy.entity.BasicEntity;

public interface InterfaceConverter <Dto extends BasicDto, Entity extends BasicEntity> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto dto);
}
