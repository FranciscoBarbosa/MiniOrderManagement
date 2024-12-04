package pt.francisco.miniordermanagement.infrastructure.adapter.out.order.persistence.jpa;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.domain.OrderRepository;
import pt.francisco.miniordermanagement.core.domain.order.Order;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
  private final OrderJpaRepository orderJpaRepository;
  private final OrderEntityMapper orderEntityMapper;

  @Override
  public Optional<Order> findOrderByOrderId(UUID orderId) {
    return null;
  }

  @Override
  public Order save(Order order) {
    return orderEntityMapper.toDomainOrder(
        orderJpaRepository.save(orderEntityMapper.toOrderDbEntity(order)));
  }
}
