package com.example.demo.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Positive;

@Data
public class PageRequestInfo {
    private int page;
    @Positive(message = "{valid.negative.error}")
    private int size;

    public PageRequest getPageRequest() {
        return PageRequest.of(page, size);
    }
}
