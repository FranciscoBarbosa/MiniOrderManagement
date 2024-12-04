package pt.francisco.miniordermanagement.core.application.port.in.booking.dto;

import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderResponseDto;
import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;

import java.time.LocalDateTime;

public record BookingResponseDto(OrderBookingNumber orderBookingNumber, LocalDateTime bookingDate, OrderResponseDto order) {
}
