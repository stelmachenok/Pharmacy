package by.samsolution.pharmacy.converter.impl;

import by.samsolution.pharmacy.converter.InterfaceConverter;
import by.samsolution.pharmacy.dto.UserDto;
import by.samsolution.pharmacy.entity.User;

public class UserConverter implements InterfaceConverter<UserDto, User> {
    @Override
    public UserDto entityToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setRole(entity.getRole());
        dto.setEnabled(entity.getEnabled());
        dto.setPharmacyId(entity.getPharmacyId());
        return dto;
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setLogin(dto.getLogin());
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.getEnabled());
        entity.setPharmacyId(dto.getPharmacyId());
        return entity;
    }
}
