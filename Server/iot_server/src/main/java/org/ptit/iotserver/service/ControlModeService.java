package org.ptit.iotserver.service;

import org.ptit.iotserver.dto.request.CreateControlModeRequest;
import org.ptit.iotserver.dto.response.ControlModeResponse;

public interface ControlModeService {
  ControlModeResponse createLamp(CreateControlModeRequest request);
  ControlModeResponse createFan(CreateControlModeRequest request);
  ControlModeResponse getLamp();
  ControlModeResponse getFan();
}
