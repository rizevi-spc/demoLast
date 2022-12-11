package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeyCustomer {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
}
