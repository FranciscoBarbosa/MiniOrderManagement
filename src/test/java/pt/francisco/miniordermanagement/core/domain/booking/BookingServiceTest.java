package pt.francisco.miniordermanagement.core.domain.booking;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.francisco.miniordermanagement.core.domain.OrderRepository;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderNotFoundException;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
  @Mock private OrderRepository orderRepository;
  private BookingService bookingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    bookingService = new BookingService(orderRepository);
  }

  @Test
  void shouldCreateBookingForExistentOrder() {
    var orderId = UUID.randomUUID();
    Order order = Order.builder().orderId(orderId).customerCode("testCustomer").build();
    when(orderRepository.findOrderByOrderId(orderId)).thenReturn(Optional.of(order));

    Booking booking = bookingService.createBookingForOrderWithId(orderId);

    Assertions.assertEquals(orderId, booking.order().getOrderId());
  }

  @Test
  void shouldThrowExceptionWhenCreatingBookingForInexistentOrder() {
    var orderId = UUID.randomUUID();
    when(orderRepository.findOrderByOrderId(orderId)).thenReturn(Optional.empty());

    Assertions.assertThrows(
        OrderNotFoundException.class,
        () -> {
          bookingService.createBookingForOrderWithId(orderId);
        });
  }
}
