package org.ptit.iotserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.dto.request.CreateDHT11Request;
import org.ptit.iotserver.dto.response.DHT11Response;
import org.ptit.iotserver.service.DHT11Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dhts")
@RequiredArgsConstructor
@Slf4j
public class DHT11Controller {

  private final DHT11Service service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DHT11Response create(@RequestBody CreateDHT11Request request) {
    log.info("(create)request : {}", request);
    return service.create(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public DHT11Response get() {
    log.info("(get)");
    return service.get();
  }
}
