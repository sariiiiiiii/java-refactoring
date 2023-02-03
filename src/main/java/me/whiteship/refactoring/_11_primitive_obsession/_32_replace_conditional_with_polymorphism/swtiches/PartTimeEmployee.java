package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.swtiches;

import java.util.List;

public class PartTimeEmployee extends Employee{

    public PartTimeEmployee(List<String> availableProjects) {
        super(availableProjects);
    }

    @Override
    public int vacationHours() {
//        return super.vacationHours();
        return 80;
    }


    /**
     * PartTimeEmployee와 TemporalEmployee는 canAccessTo가 같은 코드이기때문에 따로 구현을 안해도됨
     */
//    @Override
//    public boolean canAccessTo(String project) {
////        return super.canAccessTo(project);
//        return this.availableProjects.contains(project); // 생성자로 availableProjects를 받은 이유
//    }

}
