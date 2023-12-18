package org.ptit.iotserver.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.ptit.iotserver.constants.SoundMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sound")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Sound extends BaseEntity {
  @Enumerated(EnumType.STRING)
  private SoundMode soundMode;
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
    Sound sound = (Sound) o;
    return getId() != null && Objects.equals(getId(), sound.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
