package com.jnguetsop.multitenant.service;

import com.jnguetsop.multitenant.domain.Employee;
import com.jnguetsop.multitenant.repository.EmployeeRepository;
import com.jnguetsop.multitenant.service.impl.EmployeeServiceImpl;
import com.jnguetsop.multitenant.service.mapper.EmployeeMapper;
import com.jnguetsop.multitenant.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @InjectMocks
    EmployeeServiceImpl employeeService;


    @Test
    void testCreateEmployee() {
        var employeeDTO = TestUtil.createEmployeeDTO();

        when(employeeRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(employeeRepository.findByPhone(any(String.class))).thenReturn(Optional.empty());
        when(employeeMapper.toEntity(employeeDTO)).thenCallRealMethod();
        when(employeeMapper.toDTO(any(Employee.class))).thenCallRealMethod();
        when(employeeRepository.save(any(Employee.class))).then(returnsFirstArg());

        var savedEmployee = employeeService.createEmployee(employeeDTO);

        assertNotNull(savedEmployee);
        assertEquals(employeeDTO.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employeeDTO.getLastName(), savedEmployee.getLastName());
        assertEquals(employeeDTO.getEmail(), savedEmployee.getEmail());
        assertEquals(employeeDTO.getPhone(), savedEmployee.getPhone());
        assertEquals(employeeDTO.getAddress(), savedEmployee.getAddress());
        assertEquals(employeeDTO.getCity(), savedEmployee.getCity());
    }
}
