package pt.francisco.miniordermanagement.core.application.port.in.booking.mappers;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.francisco.miniordermanagement.core.application.port.in.booking.dto.BookingResponseDto;
import pt.francisco.miniordermanagement.core.domain.booking.Booking;
import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;
import pt.francisco.miniordermanagement.core.domain.order.OrderTestData;

@ExtendWith(MockitoExtension.class)
class BookingResponseMapperTest {
  private final BookingResponseMapper bookingResponseMapper = new BookingResponseMapper();

  @Test
  void shouldMapToBookingResponse() {
    BookingResponseDto bookingResponseDto =
        bookingResponseMapper.map(
            Booking.builder()
                .orderBookingNumber(new OrderBookingNumber("123"))
                .bookingDate(LocalDateTime.of(10, 12, 21, 4, 4))
                .order(OrderTestData.createDefaultDomainOrder())
                .build());

    Assertions.assertThat(bookingResponseDto.orderBookingNumber().toString()).isEqualTo("123");
    Assertions.assertThat(bookingResponseDto.bookingDate().toString())
        .isEqualTo("0010-12-21T04:04");
    Assertions.assertThat(bookingResponseDto.order().orderId().toString())
        .isEqualTo("87ab49ec-bf6f-48b1-8044-57cb92853600");
  }
}
