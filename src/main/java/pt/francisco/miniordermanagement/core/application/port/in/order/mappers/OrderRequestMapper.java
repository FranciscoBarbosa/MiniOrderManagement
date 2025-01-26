package pt.francisco.miniordermanagement.core.application.port.in.order.mappers;

import java.util.List;

import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

@Component
public class OrderRequestMapper {
	public Order map(final OrderRequestDto orderDto) {
		List<Orderline> orderlineList = orderDto.orderlineList().stream().map(this::map).toList();
		return Order.builder()
				.orderId(orderDto.orderId())
				.orderDate(orderDto.orderDate())
				.customerCode(orderDto.customerCode())
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
