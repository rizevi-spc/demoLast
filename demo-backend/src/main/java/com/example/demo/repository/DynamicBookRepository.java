package com.example.demo.repository;

import com.example.demo.dto.BookSearchRequest;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.entity.BookStock;
import org.springframework.data.domain.Page;

public interface DynamicBookRepository {
    Page<BookStock> searchBooks(BookSearchRequest name, PageRequestInfo pageRequestInfo);
}
