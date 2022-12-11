package com.example.demo.mapper;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
import com.example.demo.dto.SimpleBookDto;
import com.example.demo.entity.BookStock;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDTO(BookStock bookStock);
    @Named("toSimple")
    SimpleBookDto toSimpleDTO(BookStock bookStock);

    BookStock toEntity(BookDto userDto);

    BookStock toEntity(BookInsert bookDto);

    List<BookDto> toDTO(List<BookStock> bookStock);

    List<BookStock> toEntity(List<BookDto> dtos);

    void update(@MappingTarget BookDto bookDto, BookStock bookStock);

    void update(@MappingTarget BookStock bookStock, BookDto bookDto);
}