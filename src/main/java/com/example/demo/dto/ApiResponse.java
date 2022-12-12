package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * to display error info
 */
@Getter
@Setter
@Builder
public class ApiResponse implements Serializable {
    private int status;
    private String message;
    private Object result;
}
