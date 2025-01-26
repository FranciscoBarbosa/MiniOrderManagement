package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

public record OrderRequestDto(UUID orderId, String customerCode, LocalDateTime orderDate,
							  List<OrderlineRequestDto> orderlineList) {
	@Builder
	@JsonCreator
	public OrderRequestDto(
			@JsonProperty("orderId") final UUID orderId,
			@JsonProperty("customerCode") final String customerCode,
			@JsonProperty("orderDate") final LocalDateTime orderDate,
			@JsonProperty("orderlineList") final List<OrderlineRequestDto> orderlineList) {
		this.orderId = orderId;
		this.customerCode = customerCode;
		this.orderDate = orderDate;
		this.orderlineList = List.copyOf(orderlineList);
	}
}
