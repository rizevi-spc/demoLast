package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;
@Data
public class UserDto {
    @Email(message = "{valid.email.error}")
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<RoleDto> roles;

}
