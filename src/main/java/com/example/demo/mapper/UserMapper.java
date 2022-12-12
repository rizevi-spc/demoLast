package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

/**
 * user mapper for dto entity mapping
 */
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    UserDto toDTO(User user);
    User toEntity(UserDto userDto);
}