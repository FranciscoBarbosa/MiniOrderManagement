package pt.francisco.miniordermanagement.core.application.port.in.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderRequestDto(
    UUID orderId,
    String customerCode,
    LocalDateTime orderDate,
    List<OrderlineRequestDto> orderlineList) {}
