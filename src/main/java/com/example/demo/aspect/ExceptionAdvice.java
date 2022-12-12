package com.example.demo.aspect;

import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * exception handling advice
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final MessageSource messageSource;
    static AtomicInteger atom = new AtomicInteger(0);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        long id = generateErrorId();
        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(String.format("Internal Server Error id: %d", id))
                .build();

        log.error("Error id:{}", id, ex);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse> handleException(BindException ex) {
        long id = generateErrorId();

        ApiResponse apiResponse = ApiResponse.<List<String>>builder().status(HttpStatus.BAD_REQUEST.value())
                .message(String.format("Validation Error id: %d", id))
                .result(ex.getAllErrors()
                        .stream()
                        .filter(FieldError.class::isInstance)
                        .map(FieldError.class::cast)
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .collect(Collectors.toList()))
                .build();

        log.error("Error id:{}", id, ex);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ApiResponse> handleException(CustomValidationException ex) {
        long id = generateErrorId();

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .message(String.format("Custom Validation Error id: %d", id))
                .result(getMessage(ex, Optional.ofNullable(ex.getMethodVal())
                        .orElse(null)))
                .build();

        log.error("Error id:{}", id, ex);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> handleException(MissingServletRequestParameterException ex) {
        long id = generateErrorId();

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .message(String.format("Missing Request Parameter Error id: %d", id))
                .result(ex.getParameterName())
                .build();

        log.error("Error id:{}", id, ex);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleException(DataIntegrityViolationException ex) {
        long id = generateErrorId();

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .message(String.format("Unique contraint exception id: %d", id))
                .build();

        log.error("Error id:{}", id, ex);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(RuntimeException ex, Object... params) {
        return messageSource.getMessage(ex.getMessage(), params, Locale.getDefault());
    }

    /**
     * @return we can seach and find the error in logs with generated id based on timestamp
     */
    private long generateErrorId() {
        return Long.parseLong(System.nanoTime() + "" + (atom.getAndIncrement() % 1_000));
    }

}
