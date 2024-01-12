package com.example.demo.controller;

import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.OrderSearchRequest;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

import static java.util.function.Predicate.not;

/**
 * order controller
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * insert order
     */
    @PostMapping("add")
    public ResponseEntity<CustomerOrderDto> addOrder(@Valid @RequestBody CustomerOrderDto customerOrderDto) {
        return Optional.ofNullable(orderService.add(customerOrderDto))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    /**
     * get order by id
     */
    @GetMapping("{orderId}")
    public ResponseEntity<CustomerOrderDto> getOrderById(@PathVariable Long orderId) {
        return Optional.ofNullable(orderService.getOrderById(orderId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    /**
     * search order in interval
     */
    @PostMapping("search")
    public ResponseEntity<Page<CustomerOrderDto>> searchOrder(@Valid @RequestBody OrderSearchRequest searchRequest) {
        return Optional.ofNullable(orderService.getOrdersBetweenDates(searchRequest))
                .filter(not(Streamable::isEmpty))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }
}
