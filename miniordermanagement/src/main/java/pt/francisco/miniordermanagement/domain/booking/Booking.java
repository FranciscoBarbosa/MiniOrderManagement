package pt.francisco.miniordermanagement.domain.booking;

import lombok.Builder;
import pt.francisco.miniordermanagement.domain.order.Order;

import java.util.UUID;

@Builder
public record Booking(UUID orderBookingNumber, String customerCode, String supplierCode, String factoryCode, Order order){}
