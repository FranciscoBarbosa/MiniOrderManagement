package pt.francisco.miniordermanagement.core.domain.order;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.francisco.miniordermanagement.core.domain.OrderRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock // TODO: remove mockito from tests to the Core package. Create a test double in the package
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp(){
        orderService = new OrderService(orderRepository);
    }

    @Test
    void shouldCreateOrder() {
        Order order = OrderTestData.createDefaultDomainOrder();

        orderService.createOrder(order);

        verify(orderRepository).save(order);
    }

    @Test
    void shouldThrowExceptionWhenCreatingDuplicateOrder() {
        Order order = OrderTestData.createDefaultDomainOrder();
        when(orderRepository.findOrderByOrderId(order.getOrderId())).thenReturn(java.util.Optional.of(order));

        Assertions.assertThrows(OrderAlreadyExistsException.class, () -> orderService.createOrder(order));
    }
}