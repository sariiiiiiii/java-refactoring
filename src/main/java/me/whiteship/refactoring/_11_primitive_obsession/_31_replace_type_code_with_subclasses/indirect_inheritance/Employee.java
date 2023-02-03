package me.whiteship.refactoring._11_primitive_obsession._31_replace_type_code_with_subclasses.indirect_inheritance;

import java.util.List;

public class Employee {

    private String name;

//    private String type;
//    private String typeValue; // type -> typeValue rename

    private EmployeeType type;

    public Employee(String name, String typeValue) {
//        this.validate(typeValue);
        this.name = name;
//        this.typeValue = typeValue;
        this.type = this.employeeType(typeValue);
    }

    private EmployeeType employeeType(String typeValue) {
        return switch (typeValue) {
            case "engineer" -> new Enginner();
            case "manager" -> new Manager();
            case "salesman" -> new Salesman();
            default -> throw new IllegalArgumentException("error");
        };
    }

//    private void validate(String type) {
//        List<String> legalTypes = List.of("engineer", "manager", "salesman");
//        if (!legalTypes.contains(type)) {
//            throw new IllegalArgumentException(type);
//        }
//    }

    public String capitalizedType() {
//        return this.typeValue.substring(0, 1).toUpperCase() + this.typeValue.substring(1).toLowerCase(); // EmployeeType 쪽으로 위임할 수 있음
        return this.type.capitalizedType();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", type='" + type.toString() + '\'' +
                '}';
    }
}
