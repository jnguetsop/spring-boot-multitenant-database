package com.jnguetsop.multitenant.service;

import com.jnguetsop.multitenant.service.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);

    EmployeeDTO findEmployeeById(Long id);

    EmployeeDTO findEmployeeByEmail(String email);

    EmployeeDTO findEmployeeByPhone(String phone);

    List<EmployeeDTO> findAllEmployeesByCity(String city);

    Page<EmployeeDTO> findAllEmployees(Pageable pageable);
}
