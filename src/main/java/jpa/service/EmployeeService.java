package jpa.service;

import jpa.model.Employee;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.Objects.nonNull;

public class EmployeeService {

    private final EntityManager entityManager;


    public EmployeeService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Employee create(Integer id, String name) {
        entityManager.getTransaction().begin();
        Employee employee = new Employee(id, name);
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    public void delete(Integer id) {
        Employee employee = find(id);
        if (nonNull(employee)) {
            entityManager.getTransaction().begin();
            entityManager.remove(employee);
            entityManager.getTransaction().commit();
        }
    }

    public void deleteAll() {
        for (Employee employee : findAll()) {
            delete(employee.getId());
        }
    }

    public Employee find(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

}
