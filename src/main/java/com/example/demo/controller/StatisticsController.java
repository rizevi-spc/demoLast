package com.example.demo.controller;

import com.example.demo.dto.OrderStatistics;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * statistics controller
 */
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final OrderService orderService;

    /**
     * get statistitsc
     */
    @GetMapping
    public ResponseEntity<List<OrderStatistics>> getStatistics() {
        return Optional.ofNullable(orderService.getStatistics())
                .filter(CollectionUtils::isNotEmpty)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }
}
