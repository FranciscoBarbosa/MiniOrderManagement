package pt.francisco.miniordermanagement.core.application.port.in.mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.dto.OrderlineResponseDto;
import pt.francisco.miniordermanagement.core.application.port.in.order.mappers.OrderResponseMapper;
import pt.francisco.miniordermanagement.core.domain.order.Order;
import pt.francisco.miniordermanagement.core.domain.order.Orderline;

class OrderResponseMapperTest {
  private OrderResponseMapper orderResponseMapper = new OrderResponseMapper();

  @Test
  void shouldMapFromOrderRequestToDomain() {
    UUID orderUUID = UUID.randomUUID();
    var orderlineList = List.of(new Orderline("CLTH2", 10, 5), new Orderline("FTWEAR1", 12, 8));

    var order = new Order(orderUUID, "CCODE", LocalDateTime.of(10, 12, 21, 4, 4), orderlineList);

    OrderResponseDto orderResponseDto = orderResponseMapper.map(order);

    assertThat(orderResponseDto.orderId()).isEqualTo(orderUUID);
    assertThat(orderResponseDto.customerCode()).isEqualTo("CCODE");
    assertThat(orderResponseDto.orderDate()).isEqualTo(LocalDateTime.of(10, 12, 21, 4, 4));
    assertThat(areOrderlinesEqual(orderlineList.get(0), orderResponseDto.orderlineList().get(0)))
        .isTrue();
    assertThat(areOrderlinesEqual(orderlineList.get(1), orderResponseDto.orderlineList().get(1)))
        .isTrue();
  }

  private boolean areOrderlinesEqual(
      Orderline orderline, OrderlineResponseDto orderlineResponseDto) {
    if (!Objects.equals(orderline.price(), orderlineResponseDto.price())) {
      return false;
    }
    if (!Objects.equals(orderline.quantity(), orderlineResponseDto.quantity())) {
      return false;
    }
    return Objects.equals(orderline.productId(), orderlineResponseDto.productId());
  }
}
