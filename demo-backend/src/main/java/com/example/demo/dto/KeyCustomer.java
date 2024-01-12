package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * key customer with id
 */
@Data
public class KeyCustomer implements Serializable {
    /**
     * to prevent user to send id but bi able to display it
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
}
