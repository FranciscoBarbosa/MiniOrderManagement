package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class OrderRequestDto {
  private final UUID orderId;
  private final String customerCode;
  private final LocalDateTime orderDate;
  private final List<OrderlineRequestDto> orderlineList;

  @Builder
  @JsonCreator
  public OrderRequestDto(
      @JsonProperty("orderId") UUID orderId,
      @JsonProperty("customerCode") String customerCode,
      @JsonProperty("orderDate") LocalDateTime orderDate,
      @JsonProperty("orderlineList") List<OrderlineRequestDto> orderlineList) {
    this.orderId = orderId;
    this.customerCode = customerCode;
    this.orderDate = orderDate;
    this.orderlineList = orderlineList;
  }
}
