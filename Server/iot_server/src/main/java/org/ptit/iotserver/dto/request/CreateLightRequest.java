package org.ptit.iotserver.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iotserver.entity.Light;

@Data
@NoArgsConstructor
public class CreateLightRequest {
  private Integer digitalRead;

  public Light toLight() {
    Light light = new Light();
    light.setDigitalRead(this.getDigitalRead());
    return light;
  }
}
