package pt.francisco.miniordermanagement.infrastructure.adapter.out.order.persistence.jpa;

import java.util.List;
import java.util.UUID;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

public class OrderEntityMapper {

  public OrderEntity toOrderDbEntity(Order order) {
    List<OrderlineEntity> orderlineEntityList =
        order.getOrderlineList().stream().map(this::toOrderlineDbEntity).toList();
    return OrderEntity.builder()
        .orderId(String.valueOf(order.getOrderId()))
        .customerCode(order.getCustomerCode())
        .orderDate(order.getOrderDate())
        .orderLineEntityList(orderlineEntityList)
        .build();
  }

  private OrderlineEntity toOrderlineDbEntity(Orderline orderline) {
    return OrderlineEntity.builder()
        .productId(orderline.productId())
        .price(orderline.price())
        .quantity(orderline.quantity())
        .build();
  }

  public Order toDomainOrder(OrderEntity order) {
    List<Orderline> orderlineEntityList =
        order.getOrderLineEntityList().stream().map(this::toDomainOrderline).toList();
    return Order.builder()
        .orderId(UUID.fromString(order.getOrderId()))
        .customerCode(order.getCustomerCode())
        .orderDate(order.getOrderDate())
        .orderlineList(orderlineEntityList)
        .build();
  }

  private Orderline toDomainOrderline(OrderlineEntity orderline) {
    return Orderline.builder()
        .productId(orderline.getProductId())
        .price(orderline.getPrice())
        .quantity(orderline.getQuantity())
        .build();
  }
}
