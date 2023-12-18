package org.ptit.iotserver.scheduler;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ptit.iotserver.service.ControlModeService;
import org.ptit.iotserver.service.DHT11Service;
import org.ptit.iotserver.service.LightService;
import org.ptit.iotserver.service.SoundService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DeleteTableScheduler {
  private final ControlModeService controlModeService;
  private final DHT11Service dht11Service;
  private final LightService lightService;
  private final SoundService soundService;
  @Value(value = "${application.sensor_delete.enable:true}")
  private Boolean enable;
  @Scheduled(fixedRate = 180000)
  @Transactional
  public void executeDHT11() {
    log.info("(execute)enable : {}", enable);
    if (!enable) {
      return;
    }
    try {
      dht11Service.delete();
    } catch (Exception exception) {
      log.error("exception : {}", exception.getClass().getName());
    }
  }
  @Scheduled(fixedRate = 180000)
  @Transactional
  public void executeLight() {
    log.info("(execute)enable : {}", enable);
    if (!enable) {
      return;
    }
    try {
      lightService.delete();
    } catch (Exception exception) {
      log.error("exception : {}", exception.getClass().getName());
    }
  }
  @Scheduled(fixedRate = 180000)
  @Transactional
  public void executeSound() {
    log.info("(execute)enable : {}", enable);
    if (!enable) {
      return;
    }
    try {
      soundService.delete();
    } catch (Exception exception) {
      log.error("exception : {}", exception.getClass().getName());
    }
  }
}
