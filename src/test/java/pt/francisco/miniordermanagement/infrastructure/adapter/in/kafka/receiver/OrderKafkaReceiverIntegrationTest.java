package pt.francisco.miniordermanagement.infrastructure.adapter.in.kafka.receiver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.OrderRepository;
import pt.francisco.miniordermanagement.crosscut.MiniOrderManagementLauncher;
import pt.francisco.miniordermanagement.testcontainers.kafka.KafkaContainerSetup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(classes = MiniOrderManagementLauncher.class)
class OrderKafkaReceiverIntegrationTest extends KafkaContainerSetup {
	@Value("${kafka.order.topic}")
	private String topicName;
	@Autowired
	private KafkaTemplate kafkaTemplate;
	@Autowired
	private OrderRepository orderRepository;

	@Test
	void shouldReceiveMessage() {
		var orderId = UUID.randomUUID();
		var orderlineId = UUID.randomUUID();
		var firstOrderlineRequestDto = new OrderlineRequestDto(orderlineId.toString(), 1, 1.0);
		var orderRequestDto = OrderRequestDto.builder()
				.orderId(orderId)
				.orderlineList(List.of(firstOrderlineRequestDto))
				.customerCode("Frank12")
				.orderDate(LocalDateTime.of(12, 11, 12, 1, 2, 50))
				.build();

		kafkaTemplate.send(topicName, orderRequestDto);

		await().atMost(10, TimeUnit.SECONDS).until(() -> {
			var orderEntity = orderRepository.findOrderByOrderId(orderId).orElse(null);
			return orderEntity != null;
		});

		Order orderCreated = orderRepository.findOrderByOrderId(orderId).get();

		Assertions.assertThat(orderRequestDto.orderDate()).isEqualTo(orderCreated.getOrderDate());
		Assertions.assertThat(orderRequestDto.customerCode()).isEqualTo(orderCreated.getCustomerCode());
		Assertions.assertThat(orderRequestDto.orderId()).isEqualTo(orderCreated.getOrderId());
		Assertions.assertThat(orderRequestDto.orderlineList().get(0).getProductId())
				.isEqualTo(orderCreated.getOrderlineList().get(0).productId());
		Assertions.assertThat(orderRequestDto.orderlineList().get(0).getPrice())
				.isEqualTo(orderCreated.getOrderlineList().get(0).price());
		Assertions.assertThat(orderRequestDto.orderlineList().get(0).getQuantity())
				.isEqualTo(orderCreated.getOrderlineList().get(0).quantity());
	}

}
