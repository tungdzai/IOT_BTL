package org.ptit.iotserver.repository;

import java.util.Optional;
import org.ptit.iotserver.entity.DHT11;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DHT11Repository extends JpaRepository<DHT11, String> {
  @Query(nativeQuery = true, value = "select * from dht_11 order by created_at desc limit 1")
  Optional<DHT11> get();

  @Query(
      nativeQuery = true,
      value = "DELETE FROM dht_11 WHERE created_at NOT IN (SELECT max(ds.created_at) FROM (select * from dht_11) as ds)")
  @Modifying
  void delete();
}
