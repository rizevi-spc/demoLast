package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class BookOrderDto {
    private Long id;
    @Positive(message = "{valid.negative.error}")
    private Long quantity;
    private SimpleBookDto book;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double price;
}
