package me.whiteship.refactoring._06_mutable_data._18_split_variable;

public class Order {

    /**
     * 메소드의 입력값이 내부에서 return되는 값으로 재사용되는 경우
     * 파라미터로 받은 inputValue값을 재사용하여 다시 return하여 넘겨주는 것보다
     * 새로 변수를 할당받아 사용하는 것이 좀더 명확한 코드
     */

    public double discount(double inputValue, int quantity) {
        double result = inputValue;

        if (inputValue > 50) result -= 2;
        if (quantity > 100) result -= 1;

        return result;
    }

//    public double discount(double inputValue, int quantity) {
//        if (inputValue > 50) inputValue = inputValue - 2;
//        if (quantity > 100) inputValue = inputValue - 1;
//        return inputValue;
//    }
}
