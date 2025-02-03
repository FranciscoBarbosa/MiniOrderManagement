package pt.francisco.miniordermanagement.testcontainers.kafka;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.ConfluentKafkaContainer;

@Testcontainers
public class KafkaContainerSetup {
	@Container
	static final ConfluentKafkaContainer kafka = new ConfluentKafkaContainer("confluentinc/cp-kafka:7.4.0")
			.withExposedPorts(9092);

}
