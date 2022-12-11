package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(@Valid PageRequestInfo pageRequestInfo) {
        return Optional.ofNullable(bookService.getAllBooks(pageRequestInfo))
                .filter(not(Streamable::isEmpty))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    @PostMapping("add")
    public ResponseEntity<BookDto> insertBook(@Valid @RequestBody BookInsert bookInsert) {
        return Optional.ofNullable(bookService.add(bookInsert))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    @PutMapping("update")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto) {
        return Optional.ofNullable(bookService.update(bookDto))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

}
