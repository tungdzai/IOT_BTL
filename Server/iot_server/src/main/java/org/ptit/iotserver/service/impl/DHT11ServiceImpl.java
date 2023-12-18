package org.ptit.iotserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.dto.request.CreateDHT11Request;
import org.ptit.iotserver.dto.response.DHT11Response;
import org.ptit.iotserver.repository.DHT11Repository;
import org.ptit.iotserver.service.DHT11Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class DHT11ServiceImpl implements DHT11Service {

  private final DHT11Repository repository;

  @Override
  @Transactional
  public DHT11Response create(CreateDHT11Request request) {
    log.info("(create)request : {}", request);
    return DHT11Response.from(repository.save(request.toDHT11()));
  }

  @Override
  @Transactional(readOnly = true)
  public DHT11Response get() {
    log.info("(get)");
    return repository.get().map(DHT11Response::from).orElse(new DHT11Response());
  }

  @Override
  @Transactional
  public void delete() {
    log.info("(delete)");
    repository.delete();
  }
}
