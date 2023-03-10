package me.whiteship.refactoring._05_global_data._17_encapsulate_variable;

public class Home {

    /**
     * 변수 캡슐화하기
     * Encapsulate Variable
     *
     * 메소드는 점진적으로 새로운 메소드로 변경할 수 있으나, 데이터는 한번에 모두 변경해야 한다
     * 데이터 구조를 변경하는 작업을 그보다는 조금 더 수월한 메소드 구조 변경 작업으로 대체할 수 있다
     * 데이터가 사용되는 범위가 클수록 캡슐화를 하는 것이 더 중요해진다
     *   - 함수를 사용해서 값을 변경하면 보다 쉽게 검증 로직을 추가하거나 변경에 따르는 후속 작업을 추가하는 것이 편리하다
     * 불변 데이터의 경우에는 이런 리팩토링을 적용할 필요가 없다
     */

    // getter, setter 사용
    public static void main(String[] args) {
        System.out.println(Thermostats.getTargetTemperature());
        Thermostats.setTargetTemperature(68);
        Thermostats.setReadInFahrenheit(false);
    }
}
