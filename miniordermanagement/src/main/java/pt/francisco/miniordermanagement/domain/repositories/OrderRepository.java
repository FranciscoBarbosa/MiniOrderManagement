package pt.francisco.miniordermanagement.domain.repositories;

import pt.francisco.miniordermanagement.domain.entities.Order;

import java.util.UUID;

public interface OrderRepository {

    Order findOrderByOrderId(UUID orderId);
}
