package pt.francisco.miniordermanagement.infrastructure.adapter.in.amqp.receiver;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;

import java.util.List;
import java.util.UUID;

@Testcontainers
@SpringBootTest
class OrderReceiverIntegrationTest {
	@Value("${rabbitmq.order.queue}")
	private String queueName;
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Container
	static final RabbitMQContainer rabbit = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3-management-alpine"))
			.withExposedPorts(5672, 15672);

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.rabbitmq.host", rabbit::getHost);
		registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
	}

	@Test
	void shouldReceiveMessage() {
		var orderId = UUID.randomUUID();
		var orderlineId = UUID.randomUUID();
		var firstOrderlineRequestDto = new OrderlineRequestDto(orderlineId.toString(), 1, 1.0);

		var orderRequestDto = OrderRequestDto.builder()
				.orderId(orderId)
				.orderlineList(List.of(firstOrderlineRequestDto))
				.build();

		amqpTemplate.convertAndSend(queueName, orderRequestDto);
	}

}