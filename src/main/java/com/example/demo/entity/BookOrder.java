package com.example.demo.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

/**
 * book order entity
 */
@Entity
@Data
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Positive(message = "{valid.negative.error}")
    private long quantity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="book_id")
    private BookStock book;
    private double price;
}
