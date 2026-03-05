package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ale.sistema_discapacidad_api.domain.entities.FamilyMemberEntity;

public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity, Long> {

}
