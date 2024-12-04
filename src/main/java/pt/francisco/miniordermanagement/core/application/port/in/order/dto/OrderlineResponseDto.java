package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

public record OrderlineResponseDto(String productId, int quantity, double price){}

