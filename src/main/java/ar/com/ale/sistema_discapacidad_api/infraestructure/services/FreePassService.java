package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FreePassRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.FreePassMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FreePassService {

    private final FreePassRepository freePassRepository;
    private final PersonRepository personRepository;
    private final FreePassMapper freePassMapper;

    public FreePassResponse create(FreePassRequest request) {

        PersonEntity person = personRepository.findById(
                request.getPersonId()
        ).orElseThrow(() ->
                new RuntimeException("Persona no encontrada"));

        if (person.getHealth() == null ||
                !Boolean.TRUE.equals(person.getHealth().getActiveCud())) {

            throw new RuntimeException(
                    "La persona no posee CUD vigente");
        }

        boolean exists = freePassRepository
                .existsByPersonIdAndType(
                        request.getPersonId(),
                        request.getType()
                );

        if(exists){
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "La persona ya posee un pase libre de este tipo"
                );
        }

        FreePassEntity freePass = FreePassEntity.builder()
                .person(person)
                .type(request.getType())
                .reason(request.getReason())
                .startDate(request.getStartDate())
                .status(
                        request.getStatus() != null
                                ? request.getStatus()
                                : FreePassStatus.PENDIENTE
                        )
                .active(true)
                .build();

        FreePassEntity saved =
                freePassRepository.save(freePass);

        return mapToResponse(saved);
    }

    public List<FreePassResponse> getAll() {

        return freePassRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public FreePassResponse getById(Long id) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pase libre no encontrado"));

        return mapToResponse(freePass);
    }

    public FreePassResponse update(
                Long id,
                FreePassRequest request) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pase libre no encontrado"));

        freePass.setReason(request.getReason());
        freePass.setType(request.getType());
        freePass.setStartDate(request.getStartDate());

        FreePassEntity updated =
                freePassRepository.save(freePass);

        return freePassMapper.toResponse(updated);
        }

    public FreePassResponse updateStatus(
                Long id,
                FreePassStatusRequest request) {

        FreePassEntity freePass = freePassRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pase libre no encontrado"));

        freePass.setStatus(request.getStatus());

        FreePassEntity updated =
                freePassRepository.save(freePass);

        return freePassMapper.toResponse(updated);
    }

    public void delete(Long id) {

        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pase libre no encontrado"));

        freePassRepository.delete(freePass);
    }

    private FreePassResponse mapToResponse(
            FreePassEntity entity) {

        return FreePassResponse.builder()
                .id(entity.getId())
                .personId(entity.getPerson().getId())
                .fullName(
                        entity.getPerson().getLastName()
                                + ", "
                                + entity.getPerson().getFirstName()
                )
                .type(entity.getType())
                .reason(entity.getReason())
                .startDate(entity.getStartDate())
                .active(entity.getActive())
                .build();
    }
}