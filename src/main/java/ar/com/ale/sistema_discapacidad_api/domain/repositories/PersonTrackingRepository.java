package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTrackingRepository extends JpaRepository<PersonTrackingEntity, Long> {
}
