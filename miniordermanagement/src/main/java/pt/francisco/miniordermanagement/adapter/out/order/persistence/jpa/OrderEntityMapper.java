package pt.francisco.miniordermanagement.adapter.out.order.persistence.jpa;

import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.domain.order.Order;
import pt.francisco.miniordermanagement.domain.order.Orderline;

import java.util.List;
import java.util.UUID;

@Component
public class OrderEntityMapper {

    public OrderEntity toOrderDbEntity(Order order){
        List<OrderlineEntity> orderlineEntityList = order.orderlineList().stream().map(this::toOrderlineDbEntity).toList();
        return OrderEntity.builder()
                .orderId(String.valueOf(order.orderId()))
                .customerCode(order.customerCode())
                .orderDate(order.orderDate())
                .orderLineEntityList(orderlineEntityList)
                .build();

    }

    private OrderlineEntity toOrderlineDbEntity(Orderline orderline){
        return OrderlineEntity.builder()
                .productId(orderline.productId())
                .price(orderline.price())
                .quantity(orderline.quantity())
                .build();
    }

    public Order toDomainOrder(OrderEntity order){
        List<Orderline> orderlineEntityList = order.getOrderLineEntityList().stream().map(this::toDomainOrderline).toList();
        return Order.builder()
                .orderId(UUID.fromString(order.getOrderId()))
                .customerCode(order.getCustomerCode())
                .orderDate(order.getOrderDate())
                .orderlineList(orderlineEntityList)
                .build();

    }

    private Orderline toDomainOrderline(OrderlineEntity orderline){
        return Orderline.builder()
                .productId(orderline.getProductId())
                .price(orderline.getPrice())
                .quantity(orderline.getQuantity())
                .build();
    }

}
