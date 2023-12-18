package org.ptit.iotserver.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iotserver.entity.DHT11;

@Data
@NoArgsConstructor
public class CreateDHT11Request {
  private Float temperature;
  private Float humidity;

  public DHT11 toDHT11() {
    DHT11 dht11 = new DHT11();
    dht11.setTemperature(this.getTemperature());
    dht11.setHumidity(this.getHumidity());
    return dht11;
  }
}
