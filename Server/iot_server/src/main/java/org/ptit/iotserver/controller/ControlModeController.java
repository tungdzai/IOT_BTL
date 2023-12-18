package org.ptit.iotserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.dto.request.CreateControlModeRequest;
import org.ptit.iotserver.dto.response.ControlModeResponse;
import org.ptit.iotserver.service.ControlModeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/control-modes")
@Slf4j
public class ControlModeController {

  private final ControlModeService service;

  @PostMapping("/lamp")
  @ResponseStatus(HttpStatus.CREATED)
  public ControlModeResponse createLamp(@RequestBody CreateControlModeRequest request) {
    log.info("(createLamp)request : {}", request);
    return service.createLamp(request);
  }

  @GetMapping("/lamp")
  @ResponseStatus(HttpStatus.OK)
  public ControlModeResponse getLamp() {
    log.info("(getLamp)");
    return service.getLamp();
  }

  @PostMapping("/fan")
  @ResponseStatus(HttpStatus.CREATED)
  public ControlModeResponse createFan(@RequestBody CreateControlModeRequest request) {
    log.info("(createFan)request : {}", request);
    return service.createFan(request);
  }

  @GetMapping("/fan")
  @ResponseStatus(HttpStatus.OK)
  public ControlModeResponse getFan() {
    log.info("(getFan)");
    return service.getFan();
  }
}
