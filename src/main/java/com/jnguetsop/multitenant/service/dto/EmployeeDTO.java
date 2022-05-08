package com.jnguetsop.multitenant.service.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {

    private Long id;

    @Size(max = 60)
    private String firstName;

    @NotBlank
    @Size(max = 60)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 60)
    private String email;

    @NotBlank
    @Digits(integer = 20, fraction = 0)
    @Size(max = 20)
    private String phone;

    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 60)
    private String city;
}
