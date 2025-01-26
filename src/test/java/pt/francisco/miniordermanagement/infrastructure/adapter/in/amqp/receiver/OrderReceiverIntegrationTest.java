package pt.francisco.miniordermanagement.infrastructure.adapter.in.amqp.receiver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.crosscut.MiniOrderManagementLauncher;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order.OrderEntity;
import pt.francisco.miniordermanagement.testcontainers.rabbitmq.RabbitmqContainerSetup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(classes = MiniOrderManagementLauncher.class)
@ComponentScan(basePackages = "pt.francisco.miniordermanagement")
@Testcontainers
class OrderReceiverIntegrationTest extends RabbitmqContainerSetup { // TODO: add PGSQL instead of h2
	@Value("${rabbitmq.order.queue}")
	private String queueName;
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;

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

		amqpTemplate.convertAndSend(queueName, orderRequestDto);

		await().atMost(5, TimeUnit.SECONDS).until(() -> {
			Integer count = jdbcTemplate.queryForObject(
					"SELECT COUNT(*) FROM ORDER_ENTITY o WHERE o.ORDER_ID = ?",
					Integer.class,
					orderId.toString());
			return count != null && count > 0;
		});

		OrderEntity orderReturned = jdbcTemplate.queryForObject(
				"SELECT * FROM ORDER_ENTITY o WHERE o.ORDER_ID = ?",
				new BeanPropertyRowMapper<>(OrderEntity.class),
				orderId.toString());

		Assertions.assertThat(orderRequestDto.orderDate()).isEqualTo(orderReturned.getOrderDate());
		Assertions.assertThat(orderRequestDto.customerCode()).isEqualTo(orderReturned.getCustomerCode());
		Assertions.assertThat(orderRequestDto.orderId()).isEqualTo(orderReturned.getOrderId());
		Assertions.assertThat(orderRequestDto.orderlineList().get(0).getProductId())
				.isEqualTo(orderReturned.getOrderLineEntityList().get(0).getProductId());
		Assertions.assertThat(orderRequestDto.orderlineList().get(0).getPrice())
				.isEqualTo(orderReturned.getOrderLineEntityList().get(0).getPrice());
		Assertions.assertThat(orderRequestDto.orderlineList().get(0).getQuantity())
				.isEqualTo(orderReturned.getOrderLineEntityList().get(0).getQuantity());
	}

}