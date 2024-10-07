package pt.francisco.miniordermanagement.adapter.out.order.persistence.jpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Builder
public class OrderlineEntity {
    @Id
    private String productId;
    private int quantity;
    private double price;
    @Embedded
    private EntityAttributes entityAttributes;

    public OrderlineEntity() {

    }
}
