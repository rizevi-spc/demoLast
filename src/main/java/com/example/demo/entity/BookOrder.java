package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="book_id")
    private BookStock book;
    private Double price;
}
