package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassActiveRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.BenefitEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FreePassRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IFreePassService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.FreePassMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FreePassService implements IFreePassService{

    private final FreePassRepository freePassRepository;
    private final PersonRepository personRepository;
    private final FreePassMapper freePassMapper;

    private boolean isFreePassActive(FreePassEntity freePass) {

        int currentYear = LocalDate.now().getYear();

        // El pase debe estar aprobado
        if (freePass.getStatus() != FreePassStatus.APROBADO) {
                return false;
        }

        // Si fue solicitado este año, está vigente aunque
        // todavía no tenga renovaciones
        if (freePass.getRequestDate() != null
                && freePass.getRequestDate().getYear() == currentYear) {
                return true;
        }

        // Si fue solicitado en un año anterior,
        // necesita una renovación para el año actual
        return freePass.getRenewals() != null
                && freePass.getRenewals().stream()
                        .anyMatch(renewal ->
                                Integer.valueOf(currentYear)
                                        .equals(renewal.getYear())
                        );
    }

    @Override
    public FreePassResponse create(FreePassRequest request) {

        PersonEntity person = personRepository.findById(
                request.getPersonId()
        ).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"
        ));

        if (person.getHealth() == null || !Boolean.TRUE.equals(person.getHealth().getActiveCud())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La persona no posee un CUD vigente");
        }

        if (person.getDateDeath() != null) {
                throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "No se pueden realizar operaciones porque la persona se encuentra fallecida.");
        }

        boolean exists = freePassRepository
                .existsByPersonId(
                        request.getPersonId()
                );

        if(exists){
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La persona ya posee un pase libre provincial"
                );
        }

       // Si no se envía fecha, se utiliza la fecha actual
        LocalDate requestDate =
                request.getRequestDate() != null
                        ? request.getRequestDate()
                        : LocalDate.now();

        // Determinar el estado inicial
        FreePassStatus status =
                request.getStatus() != null
                        ? request.getStatus()
                        : FreePassStatus.PENDIENTE;

        // El pase solamente está activo si:
        // 1. Está aprobado
        // 2. La solicitud corresponde al año actual
        boolean active =
                status == FreePassStatus.APROBADO
                && requestDate.getYear() == LocalDate.now().getYear();

        FreePassEntity freePass = FreePassEntity.builder()
                .person(person)
                .reason(request.getReason())
                .requestDate(requestDate)
                .status(status)
                .active(active)
                .build();

        FreePassEntity saved =
                freePassRepository.save(freePass);
        
        // Actualizar beneficio de la persona
        if (person.getBenefit() == null) {

                BenefitEntity benefit = BenefitEntity.builder()
                        .person(person)
                        .freePass(active)
                        .build();

                person.setBenefit(benefit);

        } else {

                person.getBenefit().setFreePass(active);
        }

        return freePassMapper.toResponse(saved);
    }

    @Override
    public List<FreePassResponse> readAll() {

        return freePassRepository.findAll()
            .stream()
            .map(freePassMapper::toResponse)
            .toList();
    }

    @Override
    public FreePassResponse getById(Long id) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pase libre no encontrado"
                        ));

        return freePassMapper.toResponse(freePass);
    }

    @Override
    public FreePassResponse update(FreePassRequest request, Long id) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Pase libre no encontrado"
                        ));

        freePass.setReason(request.getReason());
        freePass.setRequestDate(
            request.getRequestDate() != null
                    ? request.getRequestDate()
                    : freePass.getRequestDate()
        );

        boolean active = isFreePassActive(freePass);

        freePass.setActive(active);

        PersonEntity person = freePass.getPerson();

        if (person.getBenefit() == null) {

                BenefitEntity benefit = BenefitEntity.builder()
                        .person(person)
                        .freePass(active)
                        .build();

                person.setBenefit(benefit);

        } else {

                person.getBenefit().setFreePass(active);
        }

        FreePassEntity updated =
                freePassRepository.save(freePass);

        return freePassMapper.toResponse(updated);
        }

    @Override
    public FreePassResponse updateStatus(
                Long id,
                FreePassStatusRequest request) {

        FreePassEntity freePass = freePassRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Pase libre no encontrado"
                        ));

        if (request.getStatus() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Debe indicar un estado");
        }

        freePass.setStatus(request.getStatus());

        boolean active = isFreePassActive(freePass);

        freePass.setActive(active);

        PersonEntity person = freePass.getPerson();

        if (person.getBenefit() == null) {

                BenefitEntity benefit = BenefitEntity.builder()
                        .person(person)
                        .freePass(active)
                        .build();

                person.setBenefit(benefit);

        } else {

                person.getBenefit().setFreePass(active);
        }

        FreePassEntity updated =
                freePassRepository.save(freePass);

        return freePassMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Pase libre no encontrado"
                        ));
        PersonEntity person = freePass.getPerson();

        if (person.getBenefit() != null) {
                person.getBenefit().setFreePass(false);
        }

        freePassRepository.delete(freePass);
    }

    @Override
    public FreePassResponse updateActive(Long id,FreePassActiveRequest request) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Pase libre no encontrado"
                                )
                        );

        if (request.getActive() == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Debe indicar si el pase está activo"
                );
        }

        freePass.setActive(request.getActive());

        PersonEntity person = freePass.getPerson();

        if (person.getBenefit() != null) {
                person.getBenefit().setFreePass(request.getActive());
        }

        FreePassEntity updated =
                freePassRepository.save(freePass);

        return freePassMapper.toResponse(updated);
    }

    @Override
    public List<FreePassResponse> readByActive(Boolean active) {

        if (active == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Debe indicar si desea consultar pases activos o inactivos"
                );
        }
        return freePassRepository.findByActive(active)
                .stream()
                .map(freePassMapper::toResponse)
                .toList();
   }
}