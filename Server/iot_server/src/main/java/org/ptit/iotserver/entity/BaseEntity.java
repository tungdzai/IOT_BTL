package org.ptit.iotserver.entity;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
  @Id private String id;

  @PrePersist
  public void ensureId() {
    this.id = Objects.isNull(this.id) ? UUID.randomUUID().toString() : this.id;
  }
}
