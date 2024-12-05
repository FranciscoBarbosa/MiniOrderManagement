package pt.francisco.miniordermanagement.core.domain.booking;

import java.time.LocalDateTime;
import pt.francisco.miniordermanagement.core.domain.order.Order;

public class BookingTestData {
  public static Booking createBooking(Order order) {
    return Booking.builder()
        .orderBookingNumber(new OrderBookingNumber("123"))
        .bookingDate(LocalDateTime.of(2022, 12, 2, 1, 2))
        .order(order)
        .build();
  }
}
