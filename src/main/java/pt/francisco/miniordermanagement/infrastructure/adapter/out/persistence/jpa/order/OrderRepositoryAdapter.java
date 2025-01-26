package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderRepository;

@Component
@RequiredArgsConstructor
@Transactional
public class OrderRepositoryAdapter implements OrderRepository {
	private final OrderJpaRepository orderJpaRepository;
	private final OrderEntityMapper orderEntityMapper;

	@Override
	public Optional<Order> findOrderByOrderId(final UUID orderId) {
		return orderJpaRepository
				.findById(String.valueOf(orderId))
				.map(orderEntityMapper::toDomainOrder);
	}

	@Override
	public Order save(final Order order) {
		return orderEntityMapper.toDomainOrder(
				orderJpaRepository.save(orderEntityMapper.toOrderDbEntity(order)));
	}
}
