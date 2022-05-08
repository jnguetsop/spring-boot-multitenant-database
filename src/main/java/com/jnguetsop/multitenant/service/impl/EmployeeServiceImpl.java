package com.jnguetsop.multitenant.service.impl;

import com.jnguetsop.multitenant.repository.EmployeeRepository;
import com.jnguetsop.multitenant.service.EmployeeService;
import com.jnguetsop.multitenant.service.dto.EmployeeDTO;
import com.jnguetsop.multitenant.service.exception.ArgumentNotFoundException;
import com.jnguetsop.multitenant.service.exception.FieldInUseException;
import com.jnguetsop.multitenant.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_ID_NOTFOUND_MESSAGE_TEMPLATE = "Employee with id %s was not found.";
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final DataSource dataSource;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        this.validateEmail(employeeDTO.getEmail(), null);
        this.validatePhone(employeeDTO.getPhone(), null);
        var employee = employeeMapper.toEntity(employeeDTO);

        employee = employeeRepository.save(employee);

        return employeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        this.validateEmail(employeeDTO.getEmail(), employeeDTO.getId());
        this.validatePhone(employeeDTO.getPhone(), employeeDTO.getId());

        return employeeRepository.findById(employeeDTO.getId())
                .map(employee -> {
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setEmail(employeeDTO.getEmail());
                    employee.setPhone(employeeDTO.getPhone());
                    employee.setAddress(employeeDTO.getAddress());
                    employee.setCity(employeeDTO.getCity());

                    return employeeMapper.toDTO(employee);
                })
                .orElseThrow(() -> new ArgumentNotFoundException(EMPLOYEE_ID_NOTFOUND_MESSAGE_TEMPLATE.formatted(employeeDTO.getId())));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .map(Optional::of)
                .orElseThrow(() -> new ArgumentNotFoundException(EMPLOYEE_ID_NOTFOUND_MESSAGE_TEMPLATE.formatted(id)))
                .ifPresent(employeeRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDTO)
                .orElseThrow(() -> new ArgumentNotFoundException(EMPLOYEE_ID_NOTFOUND_MESSAGE_TEMPLATE.formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(employeeMapper::toDTO)
                .orElseThrow(() -> new ArgumentNotFoundException("Employee with email %s was not found.".formatted(email)));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO findEmployeeByPhone(String phone) {
        return employeeRepository.findByPhone(phone)
                .map(employeeMapper::toDTO)
                .orElseThrow(() -> new ArgumentNotFoundException("Employee with phone %s was not found.".formatted(phone)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findAllEmployeesByCity(String city) {
        return employeeRepository.findAllByCity(city)
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAllEmployees(Pageable pageable) {
        try (var connection = dataSource.getConnection()) {
            var databaseUrl = connection.getMetaData().getURL();
            log.info("Database URL is {}", databaseUrl);
        } catch (SQLException e) {
            log.error("Error getting database url", e);
        }

        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toDTO);
    }

    private void validateEmail(String email, Long id) {
        var employee = employeeRepository.findByEmail(email);

        if (employee.isPresent() && !Objects.equals(employee.get().getId(), id)) {
            throw new FieldInUseException("Email", email);
        }
    }

    private void validatePhone(String phone, Long id) {
        var employee = employeeRepository.findByPhone(phone);

        if (employee.isPresent() && !Objects.equals(employee.get().getId(), id)) {
            throw new FieldInUseException("Phone", phone);
        }
    }
}
