package pt.francisco.miniordermanagement.core.application.port.in.order;

import lombok.RequiredArgsConstructor;
import pt.francisco.miniordermanagement.core.application.port.in.UseCase;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderRequestMapper;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderResponseMapper;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderService;

@RequiredArgsConstructor
public class CreateOrderUseCase implements UseCase<OrderRequestDto, OrderResponseDto> {
  private final OrderRequestMapper orderRequestMapper;
  private final OrderResponseMapper orderResponseMapper;
  private final OrderService orderService;

  @Override
  public OrderResponseDto execute(OrderRequestDto orderDto) {
    Order order = orderRequestMapper.map(orderDto);
    return orderResponseMapper.map(orderService.createOrder(order));
  }
}
