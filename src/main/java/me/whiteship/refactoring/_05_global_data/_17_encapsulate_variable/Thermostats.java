package me.whiteship.refactoring._05_global_data._17_encapsulate_variable;

public class Thermostats {

    /**
     * public static = 글로벌 데이터
     * final 상수를 사용 = 한번 적용하고 나면 다시는 값이 바뀌지 않는 읽고 참조만 할 수 있는 상수 데이터
     *
     * 가장 쉬운 방법으로는 접근할 때 메소드로 접근하는 방법 ex) setter, getter
     * 필드를 private로 수정
     */

//    public final static Integer targetTemperature = 70; // 상수
    private static Integer targetTemperature = 70;

    private static Boolean heating = true;

    private static Boolean cooling = false;

    private static Boolean readInFahrenheit = true;

    public static Integer getTargetTemperature() {
        return targetTemperature;
    }

    public static void setTargetTemperature(Integer targetTemperature) {
        Thermostats.targetTemperature = targetTemperature;
    }

    public static Boolean getHeating() {
        //TODO validation
        return heating;
        //TODO notify
    }

    public static void setHeating(Boolean heating) {
        Thermostats.heating = heating;
    }

    public static Boolean getCooling() {
        return cooling;
    }

    public static void setCooling(Boolean cooling) {
        Thermostats.cooling = cooling;
    }

    public static Boolean getReadInFahrenheit() {
        return readInFahrenheit;
    }

    public static void setReadInFahrenheit(Boolean readInFahrenheit) {
        Thermostats.readInFahrenheit = readInFahrenheit;
    }
}
