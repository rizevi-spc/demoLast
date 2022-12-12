package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * customer jpa repository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
