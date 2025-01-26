package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;

// TODO: can we use ValueObjects here?
public record OrderResponseDto(
    UUID orderId,
    String customerCode,
    LocalDateTime orderDate,
    List<OrderlineResponseDto> orderlineList,
    OrderBookingNumber orderBookingNumber) {

    public OrderResponseDto { //compact constructor -> allows us to add extra initialization logic -> only for java records
        orderlineList = List.copyOf(orderlineList);
    }
}
