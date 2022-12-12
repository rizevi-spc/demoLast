package com.example.demo.mapper;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import org.mapstruct.Mapper;

/**
 * customer mapper for dto entity mapping
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CustomerOrderMapper.class})
public interface CustomerMapper {
    CustomerDto toDTO(Customer customer);

    Customer toEntity(CustomerDto customerDto);
}