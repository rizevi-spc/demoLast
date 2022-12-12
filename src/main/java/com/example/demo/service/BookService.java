package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
import com.example.demo.dto.PageRequestInfo;
import org.springframework.data.domain.Page;

/**
 * service for book stock operations
 */
public interface BookService {
    /**
     * get all books with pagination
     */
    Page<BookDto> getAllBooks(PageRequestInfo pageRequestInfo);

    /**
     * insert book
     */
    BookDto add(BookInsert dto);

    /**
     * update book
     */
    BookDto update(BookDto dto);
}
