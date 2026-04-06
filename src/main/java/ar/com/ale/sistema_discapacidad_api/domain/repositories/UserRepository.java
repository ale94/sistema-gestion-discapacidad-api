package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import ar.com.ale.sistema_discapacidad_api.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
