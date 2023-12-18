package org.ptit.iotserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.dto.request.CreateSoundRequest;
import org.ptit.iotserver.dto.response.SoundResponse;
import org.ptit.iotserver.service.SoundService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sounds")
@Slf4j
public class SoundController {

  private final SoundService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SoundResponse create(@RequestBody CreateSoundRequest request) {
    log.info("(create)request : {}", request);
    return service.create(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public SoundResponse get() {
    log.info("(get)");
    return service.get();
  }
}
