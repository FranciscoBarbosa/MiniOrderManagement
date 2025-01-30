package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.booking;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pt.francisco.miniordermanagement.core.domain.booking.Booking;
import pt.francisco.miniordermanagement.core.domain.booking.BookingTestData;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderTestData;
import pt.francisco.miniordermanagement.crosscut.MiniOrderManagementLauncher;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order.OrderRepositoryAdapter;

@SpringBootTest(classes = MiniOrderManagementLauncher.class)
class BookingJpaRepositoryIntegrationTest {
	@Autowired
	private BookingRepositoryAdapter bookingRepositoryAdapter;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	OrderRepositoryAdapter orderRepositoryAdapter; // TODO: this test should not use this adapter, right?

	@Test
	void shouldStoreBooking() {
		Order order = OrderTestData.createDefaultDomainOrder();
		orderRepositoryAdapter.save(
				order); // TODO: replace by a sql script to populate initial data or something similar
		Booking booking = BookingTestData.createBooking(order);

		bookingRepositoryAdapter.save(booking);

		var numberOfBookingsStored = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM booking_entity", Integer.class);
		Assertions.assertThat(numberOfBookingsStored).isEqualTo(1);
	}
}
