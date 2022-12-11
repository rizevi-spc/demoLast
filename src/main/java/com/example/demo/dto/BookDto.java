package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class BookDto extends SimpleBookDto {
    @Positive(message = "{valid.negative.error}")
    private Long quantity;
    @Positive(message = "{valid.negative.error}")
    private Double price;
}
