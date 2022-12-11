package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
import com.example.demo.dto.PageRequestInfo;
import org.springframework.data.domain.Page;

public interface BookService {
    Page<BookDto> getAllBooks(PageRequestInfo pageRequestInfo);

    BookDto add(BookInsert dto);

    BookDto update(BookDto dto);
}
