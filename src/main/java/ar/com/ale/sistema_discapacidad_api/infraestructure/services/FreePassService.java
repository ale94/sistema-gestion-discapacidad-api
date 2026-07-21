package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassActiveRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassResponse;
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

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FreePassService implements IFreePassService{

    private final FreePassRepository freePassRepository;
    private final PersonRepository personRepository;
    private final FreePassMapper freePassMapper;

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

        FreePassEntity freePass = FreePassEntity.builder()
                .person(person)
                .reason(request.getReason())
                .status(
                        request.getStatus() != null
                                ? request.getStatus()
                                : FreePassStatus.PENDIENTE
                        )
                .active(true)
                .build();

        FreePassEntity saved =
                freePassRepository.save(freePass);

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

        freePassRepository.delete(freePass);
    }

    @Override
    public FreePassResponse updateActive(Long id, FreePassActiveRequest request) {
        FreePassEntity freePass =
                freePassRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Pase libre no encontrado"
                                ));

        if (request.getActive() == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Debe indicar si el pase está activo"
                );
        }

        freePass.setActive(request.getActive());

        FreePassEntity updated = freePassRepository.save(freePass);

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