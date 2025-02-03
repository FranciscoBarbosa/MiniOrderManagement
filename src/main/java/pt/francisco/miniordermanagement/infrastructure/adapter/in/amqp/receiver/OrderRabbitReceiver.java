package pt.francisco.miniordermanagement.infrastructure.adapter.in.amqp.receiver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.core.application.port.in.order.CreateOrderUseCase;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;

@Component
@RabbitListener(queues = "${rabbitmq.order.queue}")
@RequiredArgsConstructor
@Slf4j
public class OrderRabbitReceiver {
	private final CreateOrderUseCase createOrderUseCase;

	@RabbitHandler
	public void receive(final OrderRequestDto orderRequestDto) {
		log.info("Received request to create order with id: {}. Order details {}:", orderRequestDto.orderId(),
				orderRequestDto);
		createOrderUseCase.execute(orderRequestDto);
	}
}
