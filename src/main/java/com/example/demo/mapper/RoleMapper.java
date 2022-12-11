package com.example.demo.mapper;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDTO(Role role);
    Role toEntity(RoleDto roleDto);
}