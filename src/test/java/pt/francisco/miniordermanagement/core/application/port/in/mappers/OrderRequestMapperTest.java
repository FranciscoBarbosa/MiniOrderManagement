package pt.francisco.miniordermanagement.core.application.port.in.mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineRequestDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderRequestMapper;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

class OrderRequestMapperTest {
  private OrderRequestMapper orderRequestMapper = new OrderRequestMapper();

  @Test
  void shouldMapFromOrderRequestToDomain() {
    UUID orderUUID = UUID.randomUUID();
    var orderlineList =
        List.of(new OrderlineRequestDto("CLTH2", 10, 5), new OrderlineRequestDto("FTWEAR1", 12, 8));

    var orderRequestDto =
        new OrderRequestDto(orderUUID, "CCODE", LocalDateTime.of(10, 12, 21, 4, 4), orderlineList);

    Order order = orderRequestMapper.map(orderRequestDto);

    assertThat(order.getOrderId()).isEqualTo(orderUUID);
    assertThat(order.getCustomerCode()).isEqualTo("CCODE");
    assertThat(order.getOrderDate()).isEqualTo(LocalDateTime.of(10, 12, 21, 4, 4));
    assertThat(areOrderlinesEqual(orderlineList.get(0), order.getOrderlineList().get(0))).isTrue();
    assertThat(areOrderlinesEqual(orderlineList.get(1), order.getOrderlineList().get(1))).isTrue();
  }

  private boolean areOrderlinesEqual(OrderlineRequestDto orderlineRequestDto, Orderline orderline) {
    if (!Objects.equals(orderline.price(), orderlineRequestDto.price())) {
      return false;
    }
    if (!Objects.equals(orderline.quantity(), orderlineRequestDto.quantity())) {
      return false;
    }
    return Objects.equals(orderline.productId(), orderlineRequestDto.productId());
  }
}
