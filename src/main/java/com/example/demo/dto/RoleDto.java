package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * role dto
 */
@Data
public class RoleDto implements Serializable {
    private String id;
    private String name;
}
