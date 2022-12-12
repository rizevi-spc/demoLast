package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * Role entity
 */
@Entity
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique = true)
  private String name;
}
