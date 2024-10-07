package pt.francisco.miniordermanagement.app.order.ports.in;

import lombok.RequiredArgsConstructor;
import pt.francisco.miniordermanagement.app.order.ports.in.dto.OrderDto;
import pt.francisco.miniordermanagement.domain.order.OrderService;

@RequiredArgsConstructor
public class CreateOrderUseCase implements UseCase<OrderDto, Void> {
    private final OrderDtoMapper orderMapper;
    private final OrderService orderService;
    @Override
    public Void execute(OrderDto orderDto) {
        orderService.createOrder(orderMapper.map(orderDto));
        return null;
    }
}
