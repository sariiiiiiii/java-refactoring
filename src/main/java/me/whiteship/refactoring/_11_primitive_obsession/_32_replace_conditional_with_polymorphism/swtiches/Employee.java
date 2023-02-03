package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.swtiches;

import java.util.List;

public abstract class Employee {

//    protected String type;

    protected List<String> availableProjects;

    public Employee() {
    }

    public Employee(String type, List<String> availableProjects) {
//        this.type = type;
        this.availableProjects = availableProjects;
    }

    public Employee(List<String> availableProjects) {
        this.availableProjects = availableProjects;
    }

    public abstract int vacationHours();

    public boolean canAccessTo(String project) {

        /**
         * 하위클래스에서 canAccessTo 메소드에 대해 return을 해주고 있기 때문에 상위클래스에서는 default에 해당하는 값만 return
         */

//        return switch (type) {
//            case "full-time" -> true;
//            case "part-time", "temporal" -> this.availableProjects.contains(project);
//            default -> false;
//        };

        /**
         * PartTimeEmployee와 TemporalEmployee는 canAccessTo가 같은 코드이기때문에 따로 구현을 안해도됨
         */

        return this.availableProjects.contains(project);
    }
}
