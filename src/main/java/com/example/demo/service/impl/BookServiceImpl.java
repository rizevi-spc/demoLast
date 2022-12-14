package com.example.demo.service.impl;

import com.example.demo.aspect.annotation.Logging;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
import com.example.demo.dto.BookSearchRequest;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.entity.BookStock;
import com.example.demo.exception.CustomValidationException;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

/**
 * book service implementation
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Logging
    public Page<BookDto> getAllBooks(PageRequestInfo pageRequestInfo) {
        return bookRepository.findAll(pageRequestInfo.getPageRequest()).map(bookMapper::toDTO);
    }

    @Override
    @Logging
    public Page<BookDto> searchBooks(BookSearchRequest bookSearchRequest) {
        return bookRepository.searchBooks(bookSearchRequest , bookSearchRequest.getPageRequestInfo())
                .map(bookMapper::toDTO);
    }

    @Override
    @Logging
    public BookDto add(BookInsert dto) {
        return bookMapper.toDTO(bookRepository.save(bookMapper.toEntity(dto)));
    }

    @Override
    @Logging
    public BookDto update(BookDto dto) {
        final BookStock bookStock = bookRepository.findById(dto.getId())
                .filter(Objects::nonNull)
                .map(book -> {
                    bookMapper.update(book, dto);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new CustomValidationException("valid.book.not.exist", dto, BookDto::getId));
        return bookMapper.toDTO(bookRepository.save(bookStock));
    }
}
