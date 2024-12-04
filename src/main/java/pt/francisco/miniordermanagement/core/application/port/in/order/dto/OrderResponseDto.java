package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//TODO: can we use ValueObjects here?
public record OrderResponseDto(UUID orderId, String customerCode, LocalDateTime orderDate, List<OrderlineResponseDto> orderlineList, OrderBookingNumber orderBookingNumber){}

