package pt.francisco.miniordermanagement.core.domain;

import java.util.Optional;
import java.util.UUID;
import pt.francisco.miniordermanagement.core.domain.order.Order;

// There's a conflict between DDD and Hexagonal/Clean Arch, because infra can't depend directly on
// domain
// But DDD enforces that contracts of Repositories are defined purely in the domain to deal with the
// aggregates
public interface OrderRepository {

  Optional<Order> findOrderByOrderId(UUID orderId);

  Order save(Order order);
}
