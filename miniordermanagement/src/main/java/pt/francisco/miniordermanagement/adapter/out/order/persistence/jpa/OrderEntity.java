package pt.francisco.miniordermanagement.adapter.out.order.persistence.jpa;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
public class OrderEntity {
    @Id
    private String orderId;
    private String customerCode;
    private LocalDateTime orderDate;
    @OneToMany
    private List<OrderlineEntity> orderLineEntityList;
    @Embedded
    private EntityAttributes entityAttributes;

    public OrderEntity() {

    }
}
