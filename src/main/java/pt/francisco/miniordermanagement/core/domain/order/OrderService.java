package pt.francisco.miniordermanagement.core.domain.order;

public class OrderService {
	private final OrderRepository orderRepository;

	public OrderService(final OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	// TODO: revisar isto, parece estranho estar a passar um order para criar um
	// order.
	// Devia ser talvez um wrapper com os detalhes da order pra criarmos o objeto
	// Order a partir
	// disso?
	public Order createOrder(final Order order) {
		if (orderAlreadyExists(order)) {
			throw new OrderAlreadyExistsException();
		}
		return orderRepository.save(order);
	}

	private boolean orderAlreadyExists(final Order order) {
		return orderRepository.findOrderByOrderId(order.getOrderId()).isPresent();
	}
}
