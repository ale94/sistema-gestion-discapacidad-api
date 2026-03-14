package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ale.sistema_discapacidad_api.domain.entities.EquipmentTypeEntity;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypeEntity, Long> {

}
