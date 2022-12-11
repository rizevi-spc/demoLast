package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SimpleBookDto {
    @NotNull(message = "{valid.null.error}")
    private Long id;
    @NotBlank(message = "{valid.blank.error}")
    private String name;
    private String author;
}
