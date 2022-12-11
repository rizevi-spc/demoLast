package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerOrderDto {
    private Long id;
    @Valid
    private List<BookOrderDto> bookOrders;
    private Long customerId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateDateTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDateTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double price;
}
