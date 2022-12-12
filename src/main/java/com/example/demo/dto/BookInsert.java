package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * book insert dto for inserting seperate from book dto despite of hiding of id
 */
@Data
public class BookInsert implements Serializable {

    @NotBlank(message = "{valid.blank.error}")
    private String name;
    private String author;
    @Positive(message = "{valid.negative.error}")
    private Long quantity;
    @Positive(message = "{valid.negative.error}")
    private Double price;
}
