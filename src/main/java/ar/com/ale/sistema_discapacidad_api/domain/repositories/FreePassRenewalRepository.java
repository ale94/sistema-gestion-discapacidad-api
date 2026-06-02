package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassRenewalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreePassRenewalRepository
        extends JpaRepository<FreePassRenewalEntity, Long> {
}