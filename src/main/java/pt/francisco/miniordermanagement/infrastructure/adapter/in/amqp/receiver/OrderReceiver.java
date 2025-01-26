package pt.francisco.miniordermanagement.infrastructure.adapter.in.amqp.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;

@Component
@RabbitListener(queues = "${rabbitmq.order.queue}")
public class OrderReceiver {
	@RabbitHandler
	public void receive(final OrderRequestDto orderRequestDto) {
		System.out.println("Order received: " + orderRequestDto);
	}
}
