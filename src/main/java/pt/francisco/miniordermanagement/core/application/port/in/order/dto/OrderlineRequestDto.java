package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

public record OrderlineRequestDto(String productId, int quantity, double price) {}
