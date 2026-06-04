package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassEntity;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FreePassRepository extends JpaRepository<FreePassEntity, Long> {
        boolean existsByPersonIdAndType(Long personId, FreePassType type);
}