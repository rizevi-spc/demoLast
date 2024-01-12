package com.example.demo.dto;

import lombok.Data;

import jakarta.validation.Valid;

@Data
public class BookSearchRequest {
    private String name;
    private String author;
    @Valid
    private PageRequestInfo pageRequestInfo;
}
