package org.ptit.iotserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.constants.Device;
import org.ptit.iotserver.dto.request.CreateControlModeRequest;
import org.ptit.iotserver.dto.response.ControlModeResponse;
import org.ptit.iotserver.entity.ControlMode;
import org.ptit.iotserver.repository.ControlModeRepository;
import org.ptit.iotserver.service.ControlModeService;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class ControlModeServiceImpl implements ControlModeService {

  private final ControlModeRepository repository;

  @Override
  @Transactional
  public ControlModeResponse createLamp(CreateControlModeRequest request) {
    log.info("(createLamp)request : {}", request);
    ControlMode controlMode = request.toControlMode();
    controlMode.setDevice(Device.LAMP);
    return ControlModeResponse.from(repository.save(controlMode));
  }

  @Override
  @Transactional
  public ControlModeResponse createFan(CreateControlModeRequest request) {
    log.info("(createFan)request : {}", request);
    ControlMode controlMode = request.toControlMode();
    controlMode.setDevice(Device.FAN);
    return ControlModeResponse.from(repository.save(controlMode));
  }

  @Override
  @Transactional(readOnly = true)
  public ControlModeResponse getLamp() {
    log.info("(getLamp)");
    return repository
        .get(Device.LAMP.name())
        .map(ControlModeResponse::from)
        .orElse(new ControlModeResponse());
  }

  @Override
  @Transactional(readOnly = true)
  public ControlModeResponse getFan() {
    log.info("(getFan)");
    return repository
        .get(Device.FAN.name())
        .map(ControlModeResponse::from)
        .orElse(new ControlModeResponse());
  }
}
