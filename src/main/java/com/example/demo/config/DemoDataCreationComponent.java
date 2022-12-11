package com.example.demo.config;

import com.example.demo.entity.CustomerOrder;
import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DemoDataCreationComponent {

}