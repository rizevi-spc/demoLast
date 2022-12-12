package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * type for returning page request
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PageRequestInfo implements Serializable {
    private int page;
    @Positive(message = "{valid.negative.error}")
    private int size;

    public PageRequest getPageRequest() {
        return PageRequest.of(page, size);
    }
}
