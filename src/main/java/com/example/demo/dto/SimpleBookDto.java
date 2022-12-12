package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * dto for displaying book stock info inside book order
 */
@Data
public class SimpleBookDto implements Serializable {
    @NotNull(message = "{valid.null.error}")
    private Long id;
    @NotNull(message = "{valid.null.error}")
    @NotBlank(message = "{valid.blank.error}")
    private String name;
    private String author;
}
