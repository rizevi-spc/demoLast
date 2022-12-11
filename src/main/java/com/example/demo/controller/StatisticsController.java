package com.example.demo.controller;

import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.OrderSearchRequest;
import com.example.demo.dto.OrderStatistics;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderStatistics>> getStatistics() {
        return Optional.ofNullable(orderService.getStatistics())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }
}
