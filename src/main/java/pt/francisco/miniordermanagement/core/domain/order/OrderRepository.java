package pt.francisco.miniordermanagement.core.domain.order;

import java.util.Optional;
import java.util.UUID;

// Driver ports (primary ports). They are interfaces offering the use cases of our application.
// So they belong to the application layer. They are called by the driver adapters and
// they are implemented by the application layer.

// Driven ports (secondary ports). Here it depends. A driven port is an abstraction (interface)
// for doing some task that is performed by an external actor outside your application. If such
// task is logic that is independent of whatever domain, then the port interface belongs to the
// application layer (for example a port for authentication and authorization). If the tasks are
// logic of the domain of your application, then the port belongs to the domain.
public interface OrderRepository {

	Optional<Order> findOrderByOrderId(UUID orderId);

	Order save(Order order);
}
