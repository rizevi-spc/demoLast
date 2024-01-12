package com.example.demo.entity;

import com.example.demo.enumeration.ORDER_STATUS;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * customer order entity
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_order_id")
    private List<BookOrder> bookOrders;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private ORDER_STATUS status;
    @CreatedDate
    @Column
    private LocalDateTime updateDateTime;
    @LastModifiedDate
    @Column
    private LocalDateTime createDateTime;
    private Double price;
}
