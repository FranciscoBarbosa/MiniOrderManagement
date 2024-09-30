package pt.francisco.miniordermanagement.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record Order(UUID orderId, String customerCode, LocalDateTime orderDate, List<Orderline> orderlineList){}
