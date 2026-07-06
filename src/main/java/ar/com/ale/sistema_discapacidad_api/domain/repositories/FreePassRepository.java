package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FreePassRepository extends JpaRepository<FreePassEntity, Long> {
        boolean existsByPersonId(Long personId);

        List<FreePassEntity> findByActive(Boolean active);
}