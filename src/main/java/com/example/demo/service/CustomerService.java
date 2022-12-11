package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.PageRequestInfo;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDto add(CustomerDto customerDto);

    Page<CustomerOrderDto> getOrdersOfCustomer(Long customerId, PageRequestInfo pageRequestInfo);

}
