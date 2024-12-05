package pt.francisco.miniordermanagement.infrastructure.adapter.in.amqp.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;

@Component
public class OrderReceiver {
  @RabbitListener(queues = "order-queue")
  public void receive(OrderRequestDto orderRequestDto) {
    System.out.println("Order received: " + orderRequestDto);
  }
}
