package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.InterfaceConverter;
import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.entity.MedicamentCategory;

public class CategoryConverter implements InterfaceConverter<CategoryDto, MedicamentCategory> {
    @Override
    public CategoryDto entityToDto(MedicamentCategory entity) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryName(entity.getCategoryName());
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public MedicamentCategory dtoToEntity(CategoryDto dto) {
        MedicamentCategory entity = new MedicamentCategory();
        entity.setCategoryName(dto.getCategoryName());
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getId());
        return entity;
    }
}
