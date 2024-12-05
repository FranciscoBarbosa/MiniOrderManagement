package pt.francisco.miniordermanagement.core.domain.booking;

import java.util.UUID;

import static pt.francisco.miniordermanagement.core.domain.order.OrderTestData.createDefaultDomainOrder;

public class BookingTestData {
    public static Booking createBooking() {
        return Booking.builder().orderBookingNumber(new OrderBookingNumber("123")).bookingDate(null).order(createDefaultDomainOrder()).build();
      }
}
