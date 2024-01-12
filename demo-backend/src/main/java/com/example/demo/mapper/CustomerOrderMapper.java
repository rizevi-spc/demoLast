package com.example.demo.mapper;

import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.entity.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * order mapper for dto entity mapping
 */
@Mapper(componentModel = "spring", uses = {BookOrderMapper.class, CustomerMapper.class})
public interface CustomerOrderMapper {
    @Mapping(target = "customerId", source = "customer.id")
    CustomerOrderDto toDTO(CustomerOrder customerOrder);
    @Mapping(source = "customerId", target = "customer.id")
    CustomerOrder toEntity(CustomerOrderDto customerOrderDto);
}