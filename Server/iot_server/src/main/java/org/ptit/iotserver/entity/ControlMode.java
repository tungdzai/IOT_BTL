package org.ptit.iotserver.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.ptit.iotserver.constants.Device;
import org.ptit.iotserver.constants.ManualMode;
import org.ptit.iotserver.constants.OperationMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "control_mode")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ControlMode extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private Device device;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OperationMode operationMode;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ManualMode manualMode;

  @CreatedDate private Long createdAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ControlMode that = (ControlMode) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
