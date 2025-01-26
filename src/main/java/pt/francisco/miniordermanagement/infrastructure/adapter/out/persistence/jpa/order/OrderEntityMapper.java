package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

@Component
public class OrderEntityMapper {

	public OrderEntity toOrderDbEntity(final Order order) {
		List<OrderlineEntity> orderlineEntityList = order.getOrderlineList().stream().map(this::toOrderlineDbEntity)
				.toList();
		return OrderEntity.builder()
				.orderId(String.valueOf(order.getOrderId()))
				.customerCode(order.getCustomerCode())
				.orderDate(order.getOrderDate())
				.orderLineEntityList(orderlineEntityList)
				.build();
	}

	private OrderlineEntity toOrderlineDbEntity(final Orderline orderline) {
		return OrderlineEntity.builder()
				.productId(orderline.productId())
				.price(orderline.price())
				.quantity(orderline.quantity())
				.build();
	}

	public Order toDomainOrder(final OrderEntity order) {
		List<Orderline> orderlineEntityList = order.getOrderLineEntityList().stream().map(this::toDomainOrderline)
				.toList();
		return Order.builder()
				.orderId(UUID.fromString(order.getOrderId()))
				.customerCode(order.getCustomerCode())
				.orderDate(order.getOrderDate())
				.orderlineList(orderlineEntityList)
				.build();
	}

	private Orderline toDomainOrderline(final OrderlineEntity orderline) {
		return Orderline.builder()
				.productId(orderline.getProductId())
				.price(orderline.getPrice())
				.quantity(orderline.getQuantity())
				.build();
	}
}
