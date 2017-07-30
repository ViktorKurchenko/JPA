package jpa.service;

import jpa.BaseTest;
import jpa.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeServiceTest extends BaseTest {

    private final EmployeeService employeeService = new EmployeeService(ENTITY_MANAGER);

    @Before
    public void before() {
        employeeService.deleteAll();
    }

    @Test
    public void testCreate() {
        assertThat(createEmployee(1, "Viktor")).isNotNull();
    }

    @Test
    public void testFind() {
        Employee employee = createEmployee(1, "Viktor");

        Employee foundEmployee = employeeService.find(employee.getId());

        assertThat(employee).isEqualTo(foundEmployee);
    }

    @Test
    public void testFindAll() {
        Employee employee1 = createEmployee(1, "Viktor");
        Employee employee2 = createEmployee(2, "Alina");

        List<Employee> employees = employeeService.findAll();

        assertThat(employees).isNotEmpty().hasSize(2).contains(employee1, employee2);
    }

    @Test
    public void testDelete() {
        Employee employee = createEmployee(1, "Viktor");

        employeeService.delete(employee.getId());

        assertThat(employeeService.find(employee.getId())).isNull();
    }

    private Employee createEmployee(Integer id, String name) {
        return employeeService.create(id, name);
    }

}