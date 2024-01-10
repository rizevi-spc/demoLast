package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.Positive;
import java.io.Serializable;

/**
 * book order dto
 */
@Data
public class BookOrderDto implements Serializable {
    /**
     * to prevent user to send id but bi able to display it
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Positive(message = "{valid.negative.error}")
    private Long quantity;
    private SimpleBookDto book;
    /**
     * to prevent user to price id but bi able to display it
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double price;
}
