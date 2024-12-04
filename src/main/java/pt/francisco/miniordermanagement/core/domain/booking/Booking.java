package pt.francisco.miniordermanagement.core.domain.booking;

import java.time.LocalDateTime;
import lombok.Builder;
import pt.francisco.miniordermanagement.core.domain.order.Order;

@Builder
public record Booking(
    OrderBookingNumber orderBookingNumber, LocalDateTime bookingDate, Order order) {}
