package pt.francisco.miniordermanagement.core.application.port.in.order.mappers;

import java.util.List;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

public class OrderRequestMapper {
	public Order map(final OrderRequestDto orderDto) {
		List<Orderline> orderlineList = orderDto.getOrderlineList().stream().map(this::map).toList();
		return Order.builder()
				.orderId(orderDto.getOrderId())
				.orderDate(orderDto.getOrderDate())
				.customerCode(orderDto.getCustomerCode())
				.orderlineList(orderlineList)
				.build();
	}

	private Orderline map(final OrderlineRequestDto orderlineDto) {
		return Orderline.builder()
				.price(orderlineDto.getPrice())
				.productId(orderlineDto.getProductId())
				.quantity(orderlineDto.getQuantity())
				.build();
	}
}
