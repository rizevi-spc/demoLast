package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends KeyCustomer{
   private String name;
   private String surname;
   @Valid
   private UserDto user;
   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private List<CustomerOrderDto> orders;
}
