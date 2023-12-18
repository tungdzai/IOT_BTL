package org.ptit.iotserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iotserver.constants.SoundMode;
import org.ptit.iotserver.entity.Sound;

@Data
@NoArgsConstructor
public class SoundResponse {
  private SoundMode soundMode;

  public static SoundResponse from(Sound sound) {
    SoundResponse response = new SoundResponse();
    response.setSoundMode(sound.getSoundMode());
    return response;
  }
}
