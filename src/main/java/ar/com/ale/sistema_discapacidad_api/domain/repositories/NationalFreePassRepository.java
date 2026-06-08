package ar.com.ale.sistema_discapacidad_api.domain.repositories;

import ar.com.ale.sistema_discapacidad_api.domain.entities.NationalFreePassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NationalFreePassRepository
        extends JpaRepository<NationalFreePassEntity, Long> {

    List<NationalFreePassEntity> findByPersonId(Long personId);

    boolean existsByPersonIdAndTripDate(
            Long personId,
            LocalDate tripDate
    );
}