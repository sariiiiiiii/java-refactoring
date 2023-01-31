package me.whiteship.refactoring._04_long_parameter_list._15_remove_flag_argument;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentTest {

    @Test
    void deliveryDate() {
        LocalDate placedOn = LocalDate.of(2021, 12, 15);
        Order orderFromWA = new Order(placedOn, "WA");

        Shipment shipment = new Shipment();
//        assertEquals(placedOn.plusDays(1), shipment.deliveryDate(orderFromWA, true));
//        assertEquals(placedOn.plusDays(2), shipment.deliveryDate(orderFromWA, false));

        // 메소드를 명시적으로 바꿔줌으로써 좀더 직관적인 메소드를 확인할 수 있고, 파라미터로 줄일 수 있음
        assertEquals(placedOn.plusDays(2), shipment.rushDeliveryDate(orderFromWA));
        assertEquals(placedOn.plusDays(2), shipment.regularDeliveryDate(orderFromWA));
    }

}