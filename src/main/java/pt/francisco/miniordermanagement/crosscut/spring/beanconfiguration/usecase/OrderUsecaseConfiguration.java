package pt.francisco.miniordermanagement.crosscut.spring.beanconfiguration.usecase;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.francisco.miniordermanagement.core.application.port.in.order.CreateOrderUseCase;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderRequestMapper;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderResponseMapper;
import pt.francisco.miniordermanagement.core.domain.order.OrderRepository;
import pt.francisco.miniordermanagement.core.domain.order.OrderService;

@Configuration
@EnableJpaRepositories(basePackages = "pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.*")
@EntityScan(basePackages = "pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.*")
public class OrderUsecaseConfiguration {

	@Bean
	public OrderResponseMapper orderResponseMapper() {
		return new OrderResponseMapper();
	}

	@Bean
	public OrderRequestMapper orderRequestMapper() {
		return new OrderRequestMapper();
	}

	@Bean
	public OrderService orderService(final OrderRepository orderRepository) {
		return new OrderService(orderRepository);
	}

	@Bean
	public CreateOrderUseCase createOrderUseCase(final OrderRequestMapper orderRequestMapper,
			final OrderResponseMapper orderResponseMapper, final OrderService orderService) {
		return new CreateOrderUseCase(orderRequestMapper, orderResponseMapper, orderService);
	}

}
