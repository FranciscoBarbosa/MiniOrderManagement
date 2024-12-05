package pt.francisco.miniordermanagement.core.domain.booking;

import java.time.LocalDateTime;
import java.util.UUID;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderNotFoundException;
import pt.francisco.miniordermanagement.core.domain.order.OrderRepository;

public class BookingService {
  private final OrderRepository orderRepository;
  private final BookingRepository bookingRepository;

  public BookingService(OrderRepository orderRepository, BookingRepository bookingRepository) {
    this.orderRepository = orderRepository;
    this.bookingRepository = bookingRepository;
  }

  public Booking createBookingForOrderWithId(UUID orderId) {
    Order order = findOrder(orderId);
    return createBooking(order);
  }

  private Order findOrder(UUID orderId) {
    Order order =
        orderRepository.findOrderByOrderId(orderId).orElseThrow(OrderNotFoundException::new);
    if (order.isBooked()) {
      throw new OrderAlreadyBookedException("Order is already booked.");
    }
    return order;
  }

  private Booking createBooking(Order order) {
    // TODO: this is weird, we are dealing with a transaction here, updating order and after saving
    // in booking repo, we should refactor it with aggregates
    // and do it in one go (same repository I guess)
    var orderBookingNumber = new OrderBookingNumber(UUID.randomUUID().toString());
    assignBookingToOrder(order, orderBookingNumber);
    // TODO: check if there's a better way to create Booking object
    Booking booking = new Booking(orderBookingNumber, LocalDateTime.now(), order);
    return bookingRepository.save(booking);
  }

  private void assignBookingToOrder(Order order, OrderBookingNumber orderBookingNumber) {
    order.setOrderBookingNumber(orderBookingNumber);
    orderRepository.save(order);
  }
}
