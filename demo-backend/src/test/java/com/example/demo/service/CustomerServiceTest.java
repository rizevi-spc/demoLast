package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.*;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.example.demo.service.BookServiceTest.getSingletonPageOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerOrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private CustomerMapper customerMapper = spy(new CustomerMapperImpl());
    @Spy
    private UserMapper userMapper = new UserMapperImpl();
    @Mock
    private CustomerOrderMapper orderMapper;
    @Mock
    private PasswordEncoder encoder;

    @Test
    void test_add_then_ok() {
        when(userRepository.save(any()))
                .thenReturn(new User());
        when(roleRepository.findRoleByName(any()))
                .thenReturn(Optional.of(new Role()));
        when(customerRepository.save(any()))
                .thenReturn(new Customer());
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setUser(new UserDto());
        assertNotNull(customerService.add(customerDto));
    }

    @Test
    void test_get_orders_then_ok() {
        when(orderRepository.findCustomerOrderByCustomer_Id(anyLong(), any(Pageable.class)))
                .thenReturn(getSingletonPageOf(CustomerOrder.class));
        when(orderMapper.toDTO(any()))
                .thenReturn(new CustomerOrderDto());
        assertFalse(customerService.getOrdersOfCustomer(1L, PageRequestInfo.of(0, 5))
                .isEmpty());
    }
}
