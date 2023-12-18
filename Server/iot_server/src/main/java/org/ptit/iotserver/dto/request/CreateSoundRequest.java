package org.ptit.iotserver.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iotserver.constants.SoundMode;
import org.ptit.iotserver.entity.Sound;

@Data
@NoArgsConstructor
public class CreateSoundRequest {
  private SoundMode soundMode;

  public Sound toSound() {
    Sound sound = new Sound();
    sound.setSoundMode(soundMode);
    return sound;
  }
}
