package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * customer order dto
 */
@Data
public class CustomerOrderDto implements Serializable {
    private Long id;
    @Valid
    private List<BookOrderDto> bookOrders;
    private Long customerId;
    /**
     * to prevent user to send date but be able to display
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateDateTime;
    /**
     * to prevent user to send date but be able to display
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDateTime;
    /**
     * to prevent user to send price but be able to display
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double price;
}
