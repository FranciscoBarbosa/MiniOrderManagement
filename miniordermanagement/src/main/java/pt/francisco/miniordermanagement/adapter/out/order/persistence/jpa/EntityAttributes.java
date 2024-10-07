package pt.francisco.miniordermanagement.adapter.out.order.persistence.jpa;

import jakarta.persistence.Embeddable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
public class EntityAttributes {
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime modified;

}
