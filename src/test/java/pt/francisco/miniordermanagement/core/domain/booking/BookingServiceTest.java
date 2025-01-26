package pt.francisco.miniordermanagement.core.domain.booking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderNotFoundException;
import pt.francisco.miniordermanagement.core.domain.order.OrderRepository;
import pt.francisco.miniordermanagement.core.domain.order.OrderTestData;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private BookingRepository bookingRepository;
	private BookingService bookingService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		bookingService = new BookingService(orderRepository, bookingRepository);
	}

	@Test
	void shouldCreateBookingForExistentOrder() {
		Order order = OrderTestData.createDefaultDomainOrder();
		when(orderRepository.findOrderByOrderId(order.getOrderId())).thenReturn(Optional.of(order));
		when(bookingRepository.save(any(Booking.class)))
				.thenReturn(BookingTestData.createBooking(order));

		Booking booking = bookingService.createBookingForOrderWithId(order.getOrderId());
		verify(orderRepository).save(order);

		Assertions.assertThat(booking.order().getOrderId()).isEqualTo(order.getOrderId());
		Assertions.assertThat(order.isBooked()).isTrue();
	}

	@Test
	void shouldThrowExceptionWhenCreatingBookingForInexistentOrder() {
		var orderId = UUID.randomUUID();
		when(orderRepository.findOrderByOrderId(orderId)).thenReturn(Optional.empty());

		Assertions.assertThatThrownBy(
				() -> {
					bookingService.createBookingForOrderWithId(orderId);
				})
				.isInstanceOf(OrderNotFoundException.class);
	}
}
