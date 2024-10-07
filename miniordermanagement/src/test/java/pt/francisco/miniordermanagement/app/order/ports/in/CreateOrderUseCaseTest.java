package pt.francisco.miniordermanagement.app.order.ports.in;

import org.springframework.boot.test.context.SpringBootTest;
import pt.francisco.miniordermanagement.domain.order.OrderRepository;
import pt.francisco.miniordermanagement.domain.order.OrderService;

@SpringBootTest
class CreateOrderUseCaseIntegrationTest {
    private OrderDtoMapper orderMapper = new OrderDtoMapper();
    private OrderRepository orderRepository;
    private OrderService orderService = new OrderService(orderRepository);
    private CreateOrderUseCase createOrderUseCase;

}