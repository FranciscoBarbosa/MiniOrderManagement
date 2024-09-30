package pt.francisco.miniordermanagement.domain.entities;

import lombok.Builder;

@Builder
public record Orderline(int productId, int quantity, double price){}

