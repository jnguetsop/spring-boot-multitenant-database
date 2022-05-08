package com.jnguetsop.multitenant.service.mapper;

import com.jnguetsop.multitenant.domain.Employee;
import com.jnguetsop.multitenant.service.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setAddress(employeeDTO.getAddress());
        employee.setCity(employeeDTO.getCity());

        return employee;
    }

    public EmployeeDTO toDTO(Employee employee) {
        var employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setCity(employee.getCity());

        return employeeDTO;
    }
}
