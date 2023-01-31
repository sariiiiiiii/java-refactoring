package me.whiteship.refactoring._04_long_parameter_list._15_remove_flag_argument;

import java.time.LocalDate;

public class Shipment {

    /**
     * if문으로 분기처리 되어서 실행되었던 로직들을 조건문을 분해하면서 좀더 직관적인 메소드로 리팩토링
     */

//    public LocalDate deliveryDate(Order order, boolean isRush) {
//        if (isRush) {
//            return rushDeliveryDate(order); // 빠른 배송
//        } else {
//            return regularDeliveryDate(order); // 느린 배송
//        }
//    }

    public LocalDate regularDeliveryDate(Order order) {
        int deliveryTime = switch (order.getDeliveryState()) {
            case "WA", "CA" -> 2;
            case "OR", "TX", "NY" -> 3;
            default -> 4;
        };
        return order.getPlacedOn().plusDays(deliveryTime);
    }

    public LocalDate rushDeliveryDate(Order order) {
        int deliveryTime = switch (order.getDeliveryState()) {
            case "WA", "CA", "OR" -> 1;
            case "TX", "NY", "FL" -> 2;
            default -> 3;
        };
        return order.getPlacedOn().plusDays(deliveryTime);
    }
}
