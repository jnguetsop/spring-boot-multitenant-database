package com.jnguetsop.multitenant.web.rest;

import com.jnguetsop.multitenant.service.EmployeeService;
import com.jnguetsop.multitenant.service.dto.EmployeeDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeResource {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO, HttpServletRequest request)
            throws URISyntaxException {


        log.info("Request to create employee: {}", employeeDTO);
        var createdEmployee = employeeService.createEmployee(employeeDTO);

        return ResponseEntity.created(new URI("/api/v1/employees/" + createdEmployee.getId()))
                .body(createdEmployee);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        log.info("Request to update employee: {}", employeeDTO);
        var updatedEmployee = employeeService.updateEmployee(employeeDTO);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.info("Request to delete employee: {}", id);

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        log.info("Request to get employee: {}", id);
        var employeeDTO = employeeService.findEmployeeById(id);

        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable String email) {
        log.info("Request to get employee by email: {}", email);
        var employeeDTO = employeeService.findEmployeeByEmail(email);

        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<EmployeeDTO> getEmployeeByPhone(@PathVariable String phone) {
        log.info("Request to get employee by phone: {}", phone);
        var employeeDTO = employeeService.findEmployeeByPhone(phone);

        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable) {
        log.info("Request to get all employees");
        var page = employeeService.findAllEmployees(pageable);

        var headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        headers.add("X-Total-Pages", Long.toString(page.getTotalPages()));
        headers.add("X-Page-Number", Long.toString(page.getNumber()));
        headers.add("X-Page-Size", Long.toString(page.getSize()));

        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
