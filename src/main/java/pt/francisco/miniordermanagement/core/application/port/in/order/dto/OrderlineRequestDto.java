package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class OrderlineRequestDto {
	private final String productId;
	private final int quantity;
	private final double price;

	@JsonCreator
	public OrderlineRequestDto(
			@JsonProperty("productId") final String productId,
			@JsonProperty("quantity") final int quantity,
			@JsonProperty("price") final double price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
}
