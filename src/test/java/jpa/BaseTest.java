package jpa;

import javax.persistence.EntityManager;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class BaseTest {

    private static final String EMPLOYEE_SERVICE = "EmployeeService";
    protected static final EntityManager ENTITY_MANAGER = createEntityManagerFactory(EMPLOYEE_SERVICE).createEntityManager();

}
