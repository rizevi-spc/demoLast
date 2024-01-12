package com.example.demo.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor(staticName = "of")
public class FieldValidationError implements Serializable {
    private String field;
    private String message;
}
