package pt.francisco.miniordermanagement.domain.order;

import java.util.UUID;

public interface OrderRepository {

    Order findOrderByOrderId(UUID orderId);

    Order save(Order order);
}
