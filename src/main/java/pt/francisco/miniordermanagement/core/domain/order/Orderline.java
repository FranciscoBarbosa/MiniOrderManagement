package pt.francisco.miniordermanagement.core.domain.order;

import lombok.Builder;

@Builder
public record Orderline(String productId, int quantity, double price){ }
