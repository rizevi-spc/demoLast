package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("add")
    public ResponseEntity<CustomerOrderDto> addOrder(@Valid @RequestBody CustomerOrderDto customerOrderDto) {
        return Optional.ofNullable(orderService.add(customerOrderDto))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    @GetMapping("{orderId}")
    public ResponseEntity<CustomerOrderDto> getOrderById(@PathVariable Long orderId) {
        return Optional.ofNullable(orderService.getOrderById(orderId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    @PostMapping("search")
    public ResponseEntity<Page<CustomerOrderDto>> searchOrder(@Valid @RequestBody OrderSearchRequest searchRequest) {
        return Optional.ofNullable(orderService.getOrdersBetweenDates(searchRequest))
                .filter(not(Streamable::isEmpty))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

}
