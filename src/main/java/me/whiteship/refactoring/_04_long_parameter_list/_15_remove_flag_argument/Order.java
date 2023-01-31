package me.whiteship.refactoring._04_long_parameter_list._15_remove_flag_argument;

import java.time.LocalDate;

public class Order {

    /**
     * 플래그 인수 제거하기
     * Remove Flag Argument
     *
     * 플래그는 보통 함수에 매개변수로 전달해서, 함수 내부의 로직을 분기하는데 사용한다 (조건문 = if, switch)
     * 플래그를 사용한 함수는 차이를 파악하기 어렵다
     *   - bookConcert(customer, false), bookConcert(customer, true)
     *   - bookConcert(customer), premiumBookConcert(customer)
     * 조건문 분해하기 (Decompose Condition)를 활용할 수 있다
     */

    private LocalDate placedOn;
    private String deliveryState;

    public Order(LocalDate placedOn, String deliveryState) {
        this.placedOn = placedOn;
        this.deliveryState = deliveryState;
    }

    public LocalDate getPlacedOn() {
        return placedOn;
    }

    public String getDeliveryState() {
        return deliveryState;
    }
}
