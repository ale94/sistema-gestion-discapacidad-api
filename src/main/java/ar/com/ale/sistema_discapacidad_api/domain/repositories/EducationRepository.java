package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ale.sistema_discapacidad_api.domain.entities.EducationEntity;

public interface EducationRepository extends JpaRepository<EducationEntity, Long> {

}
