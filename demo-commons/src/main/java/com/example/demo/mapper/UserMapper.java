package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

/**
 * user mapper for dto entity mapping
 */
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    UserDto toDTO(User user);
    @BeanMapping(ignoreByDefault = true)
    User toEntity(UserDto userDto);
}