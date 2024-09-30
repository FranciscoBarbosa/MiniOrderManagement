package pt.francisco.miniordermanagement.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
public record Booking(UUID orderBookingNumber, String customerCode, String supplierCode, String factoryCode, Order order){}
