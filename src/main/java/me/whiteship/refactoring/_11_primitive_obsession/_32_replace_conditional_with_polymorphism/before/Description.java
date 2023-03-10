package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.before;

public class Description {

    /**
     * 조건부 로직을 다형성으로 바꾸기
     * Replace Conditional with Polymorphism
     *
     * 복잡한 조건식을 상속과 다형성을 사용해 코드를 보다 명확하게 분리할 수 있다
     *
     * switch문을 사용해서 타입에 따라 각기 다른 로직을 사용하는 코드
     *
     * 기본 동작과 (타입에 따른) 특수한 기능이 섞여있는 경우에 상속 구조를 만들어서 기본 동작을 상위클래스에 두고
     * 특수한 기능을 하위클래스로 옮겨서 각 타입에 따른 "차이점"을 강조할 수 있다
     *
     * 모든 조건문을 다형성으로 옮겨야 하는가? 단순한 조건문은 그대로 두어도 좋다
     * 오직 복잡한 조건문을 다형성으로 활용해 좀 더 나은 코드로 만들 수 있는 경우에만 적용한다 (과용을 조심하자)
     */

}
