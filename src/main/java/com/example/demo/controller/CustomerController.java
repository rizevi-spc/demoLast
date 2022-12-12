package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.function.Predicate.not;

/**
 * customer controller
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /**
     * insert customer
     */
    @PostMapping("add")
    public ResponseEntity<CustomerDto> insertCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return Optional.ofNullable(customerService.add(customerDto))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

    /**
     * get customer orders
     */
    @GetMapping("{customerId}/orders")
    public ResponseEntity<Page<CustomerOrderDto>> getCustomerOrders(@PathVariable Long customerId,
                                                                    @Valid PageRequestInfo pageRequestInfo) {
        return Optional.ofNullable(customerService.getOrdersOfCustomer(customerId, pageRequestInfo))
                .filter(not(Streamable::isEmpty))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);

    }

}
