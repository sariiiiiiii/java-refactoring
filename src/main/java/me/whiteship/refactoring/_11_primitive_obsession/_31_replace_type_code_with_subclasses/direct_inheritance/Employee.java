package me.whiteship.refactoring._11_primitive_obsession._31_replace_type_code_with_subclasses.direct_inheritance;

import java.util.List;

public abstract class Employee {

    /**
     * String type을 받아오던것을 지우고 하위클래스로 만들고 override method를 통해 type을 return
     */

    private String name;

//    private String type;

    // 팩토리메소드라 생성자를 private로 해야 하지만 하위클래스에서는 생성자를 이용하기 때문에 protected로 변경
    protected Employee(String name) {
//        this.validate(type);
        this.name = name;
//        this.type = type;
    }

    public static Employee createEmployee(String name, String type) {
        return switch (type) {
            case "engineer", "manager", "salesman" -> new Enginner(name);
            default -> throw new IllegalArgumentException(type);
        };
    }

    private void validate(String type) {
        List<String> legalTypes = List.of("engineer", "manager", "salesman");
        if (!legalTypes.contains(type)) {
            throw new IllegalArgumentException(type);
        }
    }

    protected abstract String getType();

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", type='" + getType() + '\'' +
                '}';
    }

}
