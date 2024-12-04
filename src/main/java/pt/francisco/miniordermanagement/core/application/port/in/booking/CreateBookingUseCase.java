package pt.francisco.miniordermanagement.core.application.port.in.booking;

import lombok.RequiredArgsConstructor;
import pt.francisco.miniordermanagement.core.application.port.in.UseCase;
import pt.francisco.miniordermanagement.core.application.port.in.booking.dto.BookingRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.booking.dto.BookingResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.booking.mappers.BookingResponseMapper;
import pt.francisco.miniordermanagement.core.domain.booking.BookingService;

@RequiredArgsConstructor
public class CreateBookingUseCase implements UseCase<BookingRequestDto, BookingResponseDto> {

  private final BookingService bookingService;
  private final BookingResponseMapper bookingResponseMapper;

  @Override
  public BookingResponseDto execute(BookingRequestDto input) {
    return bookingResponseMapper.map(bookingService.createBookingForOrderWithId(input.orderId()));
  }
}
