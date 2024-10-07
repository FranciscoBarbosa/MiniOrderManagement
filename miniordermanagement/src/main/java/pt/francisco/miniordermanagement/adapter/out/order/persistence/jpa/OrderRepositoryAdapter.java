package pt.francisco.miniordermanagement.adapter.out.order.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.domain.order.Order;
import pt.francisco.miniordermanagement.domain.order.OrderRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderEntityMapper orderEntityMapper;
    @Override
    public Order findOrderByOrderId(UUID orderId) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return orderEntityMapper.toDomainOrder(
                orderJpaRepository.save(
                        orderEntityMapper.toOrderDbEntity(order)
                )
        );
    }
}
