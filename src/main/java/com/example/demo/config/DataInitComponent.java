package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataInitComponent {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    /**
     * to add a user and roles after init
     */
    @EventListener
    public void afterInit(ApplicationReadyEvent event) {
        Role role = new Role();
        role.setName("Admin");
        Role roleCustomer = new Role();
        role.setName("Customer");
        final Role admin = roleRepository.save(role);
        roleRepository.save(roleCustomer);
        User user = new User();
        user.setUserName("ali");
        user.setPassword(encoder.encode("veli"));
        user.setRoles(Collections.singletonList(admin));

        userRepository.save(user);
    }
}
