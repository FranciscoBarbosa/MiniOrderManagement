package pt.francisco.miniordermanagement.core.application.port.in.order.mappers;

import java.util.List;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineResponseDto;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

public class OrderResponseMapper {

	public OrderResponseDto map(final Order order) {
		List<OrderlineResponseDto> orderlineResponseList = order.getOrderlineList().stream().map(this::map).toList();

		return new OrderResponseDto(
				order.getOrderId(),
				order.getCustomerCode(),
				order.getOrderDate(),
				orderlineResponseList,
				order.getOrderBookingNumber());
	}

	OrderlineResponseDto map(final Orderline orderline) {
		return new OrderlineResponseDto(orderline.productId(), orderline.quantity(), orderline.price());
	}
}
