package com.jnguetsop.multitenant.util;

import com.jnguetsop.multitenant.domain.Employee;
import com.jnguetsop.multitenant.service.dto.EmployeeDTO;
import net.datafaker.Faker;

import java.util.Locale;

public class TestUtil {

    private TestUtil() {
    }

    private static final Faker faker = Faker.instance(Locale.FRANCE);

    public static Employee createEmployee() {
        var employee = new Employee();
        employee.setEmail(faker.internet().emailAddress());
        employee.setPhone(faker.phoneNumber().phoneNumber());
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        employee.setAddress(faker.address().fullAddress());
        employee.setCity(faker.address().city());

        return employee;
    }

    public static EmployeeDTO createEmployeeDTO() {
        var employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail(faker.internet().emailAddress());
        employeeDTO.setPhone(faker.phoneNumber().phoneNumber());
        employeeDTO.setFirstName(faker.name().firstName());
        employeeDTO.setLastName(faker.name().lastName());
        employeeDTO.setAddress(faker.address().fullAddress());
        employeeDTO.setCity(faker.address().city());

        return employeeDTO;
    }
}
