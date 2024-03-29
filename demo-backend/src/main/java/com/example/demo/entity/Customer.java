package com.example.demo.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

/**
 * customer entity
 */
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> orders;
}
