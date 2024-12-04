package pt.francisco.miniordermanagement.core.application.port.in.booking.mappers;

import pt.francisco.miniordermanagement.core.application.port.in.booking.dto.BookingResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderResponseMapper;
import pt.francisco.miniordermanagement.core.domain.booking.Booking;

public class BookingResponseMapper {
  // TODO: is this ok? to use a mapper from a different entity?
  OrderResponseMapper orderResponseMapper = new OrderResponseMapper();

  public BookingResponseDto map(Booking booking) {

    return new BookingResponseDto(
        booking.orderBookingNumber(),
        booking.bookingDate(),
        booking.order() != null ? orderResponseMapper.map(booking.order()) : null);
  }
}
