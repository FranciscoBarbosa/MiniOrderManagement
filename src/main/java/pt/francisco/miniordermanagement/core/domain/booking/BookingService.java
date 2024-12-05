package pt.francisco.miniordermanagement.core.domain.booking;

import java.time.LocalDateTime;
import java.util.UUID;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderNotFoundException;
import pt.francisco.miniordermanagement.core.domain.order.OrderRepository;

public class BookingService {
  private final OrderRepository orderRepository;

  public BookingService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Booking createBookingForOrderWithId(UUID orderId) {
    Order order =
        orderRepository.findOrderByOrderId(orderId).orElseThrow(OrderNotFoundException::new);
    validateOrder(order);
    // TODO: check if there's a better way to create Booking object
    return new Booking(
        new OrderBookingNumber(UUID.randomUUID().toString()), LocalDateTime.now(), order);
  }

  private void validateOrder(Order order) {
    if (order.isBooked()) {
      throw new OrderAlreadyBookedException("Order is already booked.");
    }
  }
}
