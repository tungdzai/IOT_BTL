package org.ptit.iotserver.service;

import org.ptit.iotserver.dto.request.CreateLightRequest;
import org.ptit.iotserver.dto.response.LightResponse;

public interface LightService {
  LightResponse create(CreateLightRequest request);
  LightResponse get();
  void delete();
}
