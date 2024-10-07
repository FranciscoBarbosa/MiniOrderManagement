package pt.francisco.miniordermanagement.app.order.ports.in.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDto(UUID orderId, String customerCode, LocalDateTime orderDate, List<OrderlineDto> orderlineList){}

