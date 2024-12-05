package pt.francisco.miniordermanagement.infrastructure.adapter.out.order.persistence.jpa.order;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pt.francisco.miniordermanagement.infrastructure.adapter.out.order.persistence.jpa.EntityAttributes;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderlineEntity {
  @Id private String productId;
  private int quantity;
  private double price;
  @Embedded private EntityAttributes entityAttributes;
}
