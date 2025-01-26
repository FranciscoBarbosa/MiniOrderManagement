package pt.francisco.miniordermanagement.testcontainers.rabbitmq;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class RabbitmqContainerSetup {
	@Container
	static final RabbitMQContainer rabbit = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3-management-alpine"))
			.withExposedPorts(5672, 15672);

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.rabbitmq.host", rabbit::getHost);
		registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
	}
}
