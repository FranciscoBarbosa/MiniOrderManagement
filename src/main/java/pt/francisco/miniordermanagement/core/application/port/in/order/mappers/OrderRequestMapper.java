package pt.francisco.miniordermanagement.core.application.port.in.order.mappers;

import java.util.List;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

public class OrderRequestMapper {
  public Order map(OrderRequestDto orderDto) {
    List<Orderline> orderlineList = orderDto.orderlineList().stream().map(this::map).toList();
    return Order.builder()
        .orderId(orderDto.orderId())
        .orderDate(orderDto.orderDate())
        .customerCode(orderDto.customerCode())
        .orderlineList(orderlineList)
        .build();
  }

  private Orderline map(OrderlineRequestDto orderlineDto) {
    return Orderline.builder()
        .price(orderlineDto.price())
        .productId(orderlineDto.productId())
        .quantity(orderlineDto.quantity())
        .build();
  }
}
