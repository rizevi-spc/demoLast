package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    private String name;
    private String author;
    private Long quantity;
    private Double price;
}
