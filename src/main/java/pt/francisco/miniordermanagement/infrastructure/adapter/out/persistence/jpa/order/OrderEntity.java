package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.EntityAttributes;

@Getter
@Entity
@NoArgsConstructor
public class OrderEntity {
	@Id
	private String orderId;
	private String customerCode;
	private LocalDateTime orderDate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderlineEntity> orderLineEntityList;

	@Embedded
	private EntityAttributes entityAttributes;

	@Builder
	public OrderEntity(final String orderId, final String customerCode, final LocalDateTime orderDate,
			final List<OrderlineEntity> orderLineEntityList, final EntityAttributes entityAttributes) {
		this.orderId = orderId;
		this.customerCode = customerCode;
		this.orderDate = orderDate;
		this.orderLineEntityList = List.copyOf(orderLineEntityList);
		this.entityAttributes = entityAttributes;
	}

	public static class OrderEntityBuilder {

		OrderEntityBuilder orderlineList(final List<OrderlineEntity> orderlineEntityList) {
			this.orderLineEntityList = new ArrayList<>(orderlineEntityList);
			return this;
		}

	}

}
