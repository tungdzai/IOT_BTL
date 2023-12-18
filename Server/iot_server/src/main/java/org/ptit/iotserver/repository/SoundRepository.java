package org.ptit.iotserver.repository;

import java.util.Optional;
import org.ptit.iotserver.entity.Sound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SoundRepository extends JpaRepository<Sound, String> {
  @Query(nativeQuery = true, value = "select * from sound order by created_at desc limit 1")
  Optional<Sound> get();

  @Query(
      nativeQuery = true,
      value = "DELETE FROM sound WHERE created_at NOT IN (SELECT max(ss.created_at) FROM (select * from sound) as ss)")
  @Modifying
  void delete();
}
