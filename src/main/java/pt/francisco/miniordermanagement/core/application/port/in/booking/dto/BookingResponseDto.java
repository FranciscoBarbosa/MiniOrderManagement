package pt.francisco.miniordermanagement.core.application.port.in.booking.dto;

import java.time.LocalDateTime;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderResponseDto;
import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;

public record BookingResponseDto(
    OrderBookingNumber orderBookingNumber, LocalDateTime bookingDate, OrderResponseDto order) {}
