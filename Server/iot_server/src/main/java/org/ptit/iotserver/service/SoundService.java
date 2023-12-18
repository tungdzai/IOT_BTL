package org.ptit.iotserver.service;

import org.ptit.iotserver.dto.request.CreateSoundRequest;
import org.ptit.iotserver.dto.response.SoundResponse;

public interface SoundService {
  SoundResponse create(CreateSoundRequest request);
  SoundResponse get();
  void delete();
}
