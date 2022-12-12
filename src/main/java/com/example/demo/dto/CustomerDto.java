package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

/**
 * customer dto
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends KeyCustomer{
   private String name;
   private String surname;
   /**
    * is related with user
    */
   @Valid
   private UserDto user;
   /**
    * to prevent user to send order data but be able to display in response
    */
   @JsonProperty(access = JsonProperty.Access.READ_ONLY)
   private List<CustomerOrderDto> orders;
}
