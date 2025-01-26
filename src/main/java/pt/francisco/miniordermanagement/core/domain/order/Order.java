package pt.francisco.miniordermanagement.core.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	@Setter
	private OrderBookingNumber orderBookingNumber;

	@Builder
	public Order(final UUID orderId, final String customerCode, final LocalDateTime orderDate,
			final List<Orderline> orderlineList) {
		this.orderId = orderId;
		this.customerCode = customerCode;
		this.orderDate = orderDate;
		this.orderlineList = List.copyOf(orderlineList); // avoid expose internal representation
		this.orderBookingNumber = null; // this means who uses passes the list to the builder could change the list
										// affecting this object
	} // we avoid this using a copy

	public boolean isBooked() {
		return this.orderBookingNumber != null;
	}

	public static class OrderBuilder { // to avoid builder to mutate orderlineList externally

		public OrderBuilder orderlineList(final List<Orderline> orderlineList) {
			this.orderlineList = new ArrayList<>(orderlineList);
			return this;
		}
	}

}
