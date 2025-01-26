package pt.francisco.miniordermanagement.core.application.port.in.order;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pt.francisco.miniordermanagement.core.domain.order.OrderTestData.DEFAULT_ORDER_UUID;

import java.time.LocalDateTime;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.francisco.miniordermanagement.core.application.port.in.UseCase;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderRequestMapper;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderResponseMapper;
import pt.francisco.miniordermanagement.core.domain.order.OrderService;
import pt.francisco.miniordermanagement.core.domain.order.OrderTestData;

// SpringBootTest is slower, in this case we don't need to start the spring context
@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {
	private OrderRequestMapper orderRequestMapper;
	private OrderResponseMapper orderResponseMapper;
	@Mock
	private OrderService orderService;
	private UseCase<OrderRequestDto, OrderResponseDto> createOrderUseCase;

	@BeforeEach
	void setUp() {
		orderRequestMapper = new OrderRequestMapper();
		orderResponseMapper = new OrderResponseMapper();

		createOrderUseCase = new CreateOrderUseCase(orderRequestMapper, orderResponseMapper, orderService);
	}

	@Test
	void shouldCreateOrder() {
		var orderRequestDto = OrderTestData.createDefaultOrderRequestDto();
		var domainOrder = OrderTestData.createDefaultDomainOrder();

		when(orderService.createOrder(any())).thenReturn(domainOrder);

		OrderResponseDto orderResponseDto = createOrderUseCase.execute(orderRequestDto);

		Assertions.assertThat(orderResponseDto.orderId()).isEqualTo(DEFAULT_ORDER_UUID);
		Assertions.assertThat(orderResponseDto.orderDate())
				.isEqualTo(LocalDateTime.of(10, 11, 23, 4, 3));
		Assertions.assertThat(orderResponseDto.customerCode()).isEqualTo("CCODE");
		Assertions.assertThat(
				areOrderlinesEqual(
						orderResponseDto.orderlineList().get(0), orderRequestDto.orderlineList().get(0)))
				.isTrue();
		Assertions.assertThat(
				areOrderlinesEqual(
						orderResponseDto.orderlineList().get(1), orderRequestDto.orderlineList().get(1)))
				.isTrue();
	}

	private boolean areOrderlinesEqual(
			OrderlineResponseDto orderlineResponseDto, OrderlineRequestDto orderlineRequestDto) {
		if (!Objects.equals(orderlineRequestDto.getPrice(), orderlineResponseDto.price())) {
			return false;
		}
		if (!Objects.equals(orderlineRequestDto.getQuantity(), orderlineResponseDto.quantity())) {
			return false;
		}
		return Objects.equals(orderlineRequestDto.getProductId(), orderlineResponseDto.productId());
	}
}
