package pt.francisco.miniordermanagement.core.domain.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;

@Getter
public class Order {
  private final UUID orderId;
  private final String customerCode;
  private final LocalDateTime orderDate;
  private final List<Orderline> orderlineList;
  @Setter private OrderBookingNumber orderBookingNumber;

  @Builder
  public Order(
      UUID orderId, String customerCode, LocalDateTime orderDate, List<Orderline> orderlineList) {
    this.orderId = orderId;
    this.customerCode = customerCode;
    this.orderDate = orderDate;
    this.orderlineList = orderlineList;
    this.orderBookingNumber = null;
  }

  public boolean isBooked() {
    return this.orderBookingNumber != null;
  }
}
