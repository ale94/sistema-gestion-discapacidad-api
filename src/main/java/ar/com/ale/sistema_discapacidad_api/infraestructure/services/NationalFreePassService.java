package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.NationalFreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.NationalFreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.NationalFreePassResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.NationalFreePassEntity;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.NationalFreePassRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.INationalFreePassService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.NationalFreePassMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NationalFreePassService
        implements INationalFreePassService {

    private final NationalFreePassRepository repository;
    private final PersonRepository personRepository;
    private final NationalFreePassMapper mapper;

    @Override
    public NationalFreePassResponse create(
            NationalFreePassRequest request) {

        var person =
                personRepository.findById(
                        request.getPersonId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Persona no encontrada"
                        ));

        if (person.getHealth() == null ||
                !Boolean.TRUE.equals(
                        person.getHealth().getActiveCud())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La persona no posee un CUD vigente"
            );
        }

        if (request.getFreePassExpiration() != null && person.getBenefit() != null) {
            person.getBenefit().setFreePassExpiration(request.getFreePassExpiration());
            personRepository.save(person);
        }

        NationalFreePassEntity pass =
                NationalFreePassEntity.builder()
                        .person(person)
                        .tripDate(request.getTripDate())
                        .ticketQuantity(request.getTicketQuantity())
                        .origin(request.getOrigin())
                        .destination(request.getDestination())
                        .status(request.getStatus() != null
                                ? request.getStatus()
                                : FreePassStatus.PENDIENTE)
                        .reason(request.getReason())
                        .build();

        pass = repository.save(pass);

        return mapper.toResponse(pass);
    }

    @Override
    public List<NationalFreePassResponse> readAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public NationalFreePassResponse readById(Long id) {

        var pass = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Solicitud no encontrada"
                        ));

        return mapper.toResponse(pass);
    }

    @Override
    public List<NationalFreePassResponse> readByPersonId(Long personId) {

        return repository.findByPersonId(personId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public NationalFreePassResponse update(NationalFreePassRequest request, Long id) {

        NationalFreePassEntity pass =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Solicitud no encontrada"
                                ));

        pass.setTripDate(request.getTripDate());
        pass.setTicketQuantity(request.getTicketQuantity());
        pass.setOrigin(request.getOrigin());
        pass.setDestination(request.getDestination());
        pass.setReason(request.getReason());

        NationalFreePassEntity updated = repository.save(pass);

        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {

        var pass = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Solicitud no encontrada"
                        ));

        repository.delete(pass);
    }

    @Override
    public NationalFreePassResponse updateStatus(Long id, NationalFreePassStatusRequest request) {

        NationalFreePassEntity pass =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Solicitud no encontrada"
                                ));

        if (request.getStatus() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe indicar un estado"
            );
        }

        pass.setStatus(request.getStatus());

        NationalFreePassEntity updated = repository.save(pass);

        return mapper.toResponse(updated);
    }
}