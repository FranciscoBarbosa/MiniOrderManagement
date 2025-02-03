package pt.francisco.miniordermanagement.infrastructure.adapter.in.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaReceiver {

	@KafkaListener(topics = "${kafka.order.topic}")
	public void processMessage(final String content) {

	}

}
