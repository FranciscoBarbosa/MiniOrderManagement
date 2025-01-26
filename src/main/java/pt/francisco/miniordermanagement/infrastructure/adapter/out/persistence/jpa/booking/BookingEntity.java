package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.booking;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.EntityAttributes;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order.OrderEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
	@Id
	private String orderBookingNumber;
	private String bookingDate;
	@OneToOne
	private OrderEntity order;
	@Embedded
	private EntityAttributes entityAttributes;
}
