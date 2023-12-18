package org.ptit.iotserver.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dht_11")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class DHT11 extends BaseEntity {
  private Float temperature;
  private Float humidity;
  @CreatedDate
  private Long createdAt;
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    DHT11 dht11 = (DHT11) o;
    return getId() != null && Objects.equals(getId(), dht11.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
