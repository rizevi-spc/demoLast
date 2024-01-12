package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
import com.example.demo.dto.BookSearchRequest;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Optional;

import static java.util.function.Predicate.not;

/**
 * controller for book stock operations
 */
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    /**
     * get all book info as pageable request is validated
     * @param pageRequestInfo page request
     * @return response
     */
    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(@Valid PageRequestInfo pageRequestInfo, Principal principal) {
        return Optional.ofNullable(bookService.getAllBooks(pageRequestInfo))
                .filter(not(Streamable::isEmpty))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    /**
     * search all book info as pageable request is validated
     * @param pageRequestInfo page request
     * @return response
     */
    @PostMapping("search")
    public ResponseEntity<Page<BookDto>> searchAllBooks(@Valid @RequestBody BookSearchRequest pageRequestInfo) {
        return Optional.ofNullable(bookService.searchBooks(pageRequestInfo))
                .filter(not(Streamable::isEmpty))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    /**
     * insert book to db request is validated
     * @param bookInsert request dto
     * @return response
     */
    @PostMapping("add")
    public ResponseEntity<BookDto> insertBook(@Valid @RequestBody BookInsert bookInsert) {
        return Optional.ofNullable(bookService.add(bookInsert))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    /**
     * update book api request is validated
     * @param bookDto request dto
     * @return response
     */
    @PutMapping("update")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto) {
        return Optional.ofNullable(bookService.update(bookDto))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

}
