package com.example.demo.service.impl;

import com.example.demo.aspect.annotation.Logging;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.mapper.CustomerOrderMapper;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final CustomerOrderRepository orderRepository;
    private final PasswordEncoder encoder;
    private final CustomerMapper customerMapper;

    private final CustomerOrderMapper customerOrderMapper;

    @Override
    @Logging
    public CustomerDto add(CustomerDto customerDto) {
        final Customer customer = customerMapper.toEntity(customerDto);
        customer.setUser(saveUser(customer));
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    private User saveUser(Customer customer) {
        final User user = customer.getUser();
        user.setPassword(encoder.encode(user.getPassword()));
        roleRepository.findRoleByName("Customer")
                .map(Collections::singletonList)
                .ifPresent(user::setRoles);
        return userRepository.save(user);
    }

    @Override
    @Logging
    public Page<CustomerOrderDto> getOrdersOfCustomer(Long customerId, PageRequestInfo pageRequestInfo) {
        return orderRepository.findCustomerOrderByCustomer_Id(customerId, pageRequestInfo.getPageRequest())
                .map(customerOrderMapper::toDTO);
    }
}
