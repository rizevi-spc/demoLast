package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class BookInsert {

    @NotBlank(message = "{valid.blank.error}")
    private String name;
    private String author;
    @Positive(message = "{valid.negative.error}")
    private Long quantity;
    @Positive(message = "{valid.negative.error}")
    private Double price;
}
