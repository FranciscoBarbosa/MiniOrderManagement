package pt.francisco.miniordermanagement.app.order.ports.in;

import org.springframework.stereotype.Component;
import pt.francisco.miniordermanagement.app.order.ports.in.dto.OrderDto;
import pt.francisco.miniordermanagement.app.order.ports.in.dto.OrderlineDto;
import pt.francisco.miniordermanagement.domain.order.Order;
import pt.francisco.miniordermanagement.domain.order.Orderline;

import java.util.List;

@Component
public class OrderDtoMapper {
    public Order map(OrderDto orderDto){
        List<Orderline> orderlineList = orderDto.orderlineList().stream().map(this::map).toList();
        return Order.builder()
                .orderId(orderDto.orderId())
                .orderDate(orderDto.orderDate())
                .customerCode(orderDto.customerCode())
                .orderlineList(orderlineList)
                .build();
    }

    private Orderline map(OrderlineDto orderlineDto){
        return Orderline.builder()
                .price(orderlineDto.price())
                .productId(orderlineDto.productId())
                .quantity(orderlineDto.quantity())
                .build();
    }
}
