package com.jnguetsop.multitenant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    @Column(name = "first_name", length = 60)
    private String firstName;

    @NotBlank
    @Size(max = 60)
    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 60)
    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "phone", length = 20, nullable = false, unique = true)
    private String phone;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @NotBlank
    @Size(max = 60)
    @Column(name = "city", length = 60, nullable = false)
    private String city;
}
