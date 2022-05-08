package com.jnguetsop.multitenant.repository;

import com.jnguetsop.multitenant.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void findByEmail() {
        var employee = TestUtil.createEmployee();

        var savedEmployee = entityManager.persist(employee);

        var foundEmployee = employeeRepository.findByEmail(savedEmployee.getEmail());

        assertThat(foundEmployee).isPresent();
        assertEquals(savedEmployee, foundEmployee.get());
    }

    @Test
    void findByPhone() {
        var employee = TestUtil.createEmployee();

        var savedEmployee = entityManager.persist(employee);

        var foundEmployee = employeeRepository.findByPhone(savedEmployee.getPhone());

        assertThat(foundEmployee).isPresent();
        assertEquals(savedEmployee, foundEmployee.get());
    }
}
