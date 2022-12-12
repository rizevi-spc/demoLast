package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.PageRequestInfo;
import org.springframework.data.domain.Page;

/**
 * service for customer operations
 */
public interface CustomerService {

    /**
     * insert customer
     */
    CustomerDto add(CustomerDto customerDto);

    /**
     * get all customer orders with pagination
     */
    Page<CustomerOrderDto> getOrdersOfCustomer(Long customerId, PageRequestInfo pageRequestInfo);

}
