package pt.francisco.miniordermanagement.infrastructure.adapter.in.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
	static final String EXCHANGE_NAME = "order-exchange";
	static final String QUEUE_NAME = "order-queue";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	Binding binding(final Queue queue, final DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).withQueueName();
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
