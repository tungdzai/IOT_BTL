package org.ptit.iotserver.service;

import org.ptit.iotserver.dto.request.CreateDHT11Request;
import org.ptit.iotserver.dto.response.DHT11Response;

public interface DHT11Service {
  DHT11Response create(CreateDHT11Request request);
  DHT11Response get();
  void delete();
}
