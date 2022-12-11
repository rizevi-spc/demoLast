package com.example.demo.exception;

import lombok.Getter;

import java.util.function.Function;

@Getter
public class CustomValidationException extends RuntimeException {
    private Object methodVal;

    public CustomValidationException(String message) {
        super(message);
    }

    public <T> CustomValidationException(String message, Object methodVal) {
        super(message);
        this.methodVal = methodVal;
    }
    public <T> CustomValidationException(String message, T type, Function<T, Object> methodRef) {
        super(message);
        this.methodVal = methodRef.apply(type);
    }
}
