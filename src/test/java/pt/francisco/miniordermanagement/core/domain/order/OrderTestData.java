package pt.francisco.miniordermanagement.core.domain.order;

import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderTestData {
    public static UUID DEFAULT_ORDER_UUID = UUID.fromString("87ab49ec-bf6f-48b1-8044-57cb92853600");

    public static Order createDefaultDomainOrder() {
        var orderlineList = List.of(
                new Orderline("CLTH2", 10, 5),
                new Orderline("FTWEAR1", 12, 8));

        return Order.builder()
                .orderId(DEFAULT_ORDER_UUID)
                .customerCode("CCODE")
                .orderDate(LocalDateTime.of(10,11,23,4,3))
                .orderlineList(orderlineList)
                .build();
    }

    public static OrderRequestDto createDefaultOrderRequestDto() {
        var orderlineRequestDtoList = List.of(
                new OrderlineRequestDto("CLTH2", 10, 5),
                new OrderlineRequestDto("FTWEAR1", 12, 8));

        return OrderRequestDto.builder()
                .orderId(DEFAULT_ORDER_UUID)
                .customerCode("CCODE")
                .orderDate(LocalDateTime.of(10,11,23,4,3))
                .orderlineList(orderlineRequestDtoList)
                .build();
    }
}
