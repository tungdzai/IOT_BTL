package org.ptit.iotserver.configuration;

import org.ptit.iotserver.repository.ControlModeRepository;
import org.ptit.iotserver.repository.DHT11Repository;
import org.ptit.iotserver.repository.LightRepository;
import org.ptit.iotserver.repository.SoundRepository;
import org.ptit.iotserver.service.ControlModeService;
import org.ptit.iotserver.service.DHT11Service;
import org.ptit.iotserver.service.LightService;
import org.ptit.iotserver.service.SoundService;
import org.ptit.iotserver.service.impl.ControlModeServiceImpl;
import org.ptit.iotserver.service.impl.DHT11ServiceImpl;
import org.ptit.iotserver.service.impl.LightServiceImpl;
import org.ptit.iotserver.service.impl.SoundServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJpaAuditing
@EnableScheduling
public class IOTServerConfiguration {

  @Bean
  public ControlModeService controlModeService(ControlModeRepository repository) {
    return new ControlModeServiceImpl(repository);
  }

  @Bean
  public DHT11Service dht11Service(DHT11Repository repository) {
    return new DHT11ServiceImpl(repository);
  }

  @Bean
  public LightService lightService(LightRepository repository) {
    return new LightServiceImpl(repository);
  }

  @Bean
  public SoundService soundService(SoundRepository repository) {
    return new SoundServiceImpl(repository);
  }
}
