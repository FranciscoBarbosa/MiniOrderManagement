package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Embeddable
public class EntityAttributes {
  @CreatedDate private LocalDateTime created;
  @LastModifiedDate private LocalDateTime modified;
}
