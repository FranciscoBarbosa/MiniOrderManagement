package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.booking;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.domain.booking.Booking;
import pt.francisco.miniordermanagement.core.domain.booking.OrderBookingNumber;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order.OrderEntityMapper;

@Component
@RequiredArgsConstructor
public class BookingEntityMapper {
	private final OrderEntityMapper orderEntityMapper;

	public BookingEntity toDbEntity(final Booking booking) {
		return BookingEntity.builder()
				.orderBookingNumber(booking.orderBookingNumber().toString())
				.bookingDate(booking.bookingDate().toString())
				.order(orderEntityMapper.toOrderDbEntity(booking.order()))
				.build();
	}

	public Booking toDomainEntity(final BookingEntity bookingEntity) {
		return Booking.builder()
				.orderBookingNumber(new OrderBookingNumber(bookingEntity.getOrderBookingNumber()))
				.bookingDate(LocalDateTime.parse(bookingEntity.getBookingDate()))
				.order(orderEntityMapper.toDomainOrder(bookingEntity.getOrder()))
				.build();
	}
}
