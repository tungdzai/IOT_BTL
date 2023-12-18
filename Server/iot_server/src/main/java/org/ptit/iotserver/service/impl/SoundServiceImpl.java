package org.ptit.iotserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.dto.request.CreateSoundRequest;
import org.ptit.iotserver.dto.response.SoundResponse;
import org.ptit.iotserver.entity.Sound;
import org.ptit.iotserver.repository.SoundRepository;
import org.ptit.iotserver.service.SoundService;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class SoundServiceImpl implements SoundService {

  private final SoundRepository repository;

  @Override
  @Transactional
  public SoundResponse create(CreateSoundRequest request) {
    log.info("(create)request : {}", request);
    return SoundResponse.from(repository.save(request.toSound()));
  }

  @Override
  @Transactional(readOnly = true)
  public SoundResponse get() {
    log.info("(get)");
    return repository.get().map(SoundResponse::from).orElse(new SoundResponse());
  }

  @Override
  @Transactional
  public void delete() {
    log.info("(delete)");
    repository.delete();
  }
}
