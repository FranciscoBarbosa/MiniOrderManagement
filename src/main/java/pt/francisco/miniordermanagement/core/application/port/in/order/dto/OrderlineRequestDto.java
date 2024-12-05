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
      @JsonProperty("productId") String productId,
      @JsonProperty("quantity") int quantity,
      @JsonProperty("price") double price) {
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }
}
