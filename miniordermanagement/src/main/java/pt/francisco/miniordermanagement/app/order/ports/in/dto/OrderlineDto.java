package pt.francisco.miniordermanagement.app.order.ports.in.dto;

public record OrderlineDto(String productId, int quantity, double price){}

