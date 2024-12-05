package pt.francisco.miniordermanagement.infrastructure.adapter.out.order.persistence.jpa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
  @Id private String orderId;
  private String customerCode;
  private LocalDateTime orderDate;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderlineEntity> orderLineEntityList;
  @Embedded private EntityAttributes entityAttributes;
}
