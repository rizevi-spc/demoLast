package com.example.demo.mapper;

import com.example.demo.dto.BookOrderDto;
import com.example.demo.entity.BookOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface BookOrderMapper {
    @Mapping(target = "book", source = "book", qualifiedByName = "toSimple")
    BookOrderDto toDTO(BookOrder bookOrder);

    BookOrder toEntity(BookOrderDto bookOrderDto);

    List<BookOrderDto> toDTO(List<BookOrder> bookOrder);

    List<BookOrder> toEntity(List<BookOrderDto> bookOrderDto);

    void update(@MappingTarget BookOrderDto bookOrderDto, BookOrder bookOrder);

    void update(@MappingTarget BookOrder bookOrder, BookOrderDto bookOrderDto);
}