package me.whiteship.refactoring._11_primitive_obsession._31_replace_type_code_with_subclasses.direct_inheritance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void employeeType() {
        assertEquals("engineer", Employee.createEmployee("sari", "engineer"));
        assertEquals("manager", Employee.createEmployee("sari", "manager"));
        assertEquals("salesman", Employee.createEmployee("sari", "salesman"));
//        assertEquals("manager", new Employee("keesun", "manager").getType());
        assertThrows(IllegalArgumentException.class, () -> Employee.createEmployee("sari", "wrong type"));
    }

}