package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * user dto
 */
@Data
public class UserDto implements Serializable {
    @NotNull(message = "{valid.null.error}")
    @Email(message = "{valid.email.error}")
    private String userName;
    /**
     * to prevent user from read but be ablo to send
     */
    @NotNull(message = "{valid.null.error}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<RoleDto> roles;

}
