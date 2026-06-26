package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRenewalRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassRenewalResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassRenewalEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FreePassRenewalRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FreePassRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.FreePassRenewalMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IFreePassRenewalService;
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
public class FreePassRenewalService implements IFreePassRenewalService{

    private final FreePassRepository freePassRepository;
    private final FreePassRenewalRepository renewalRepository;
    private final FreePassRenewalMapper renewalMapper;

    @Override
    public FreePassRenewalResponse create( FreePassRenewalRequest request ) {

        var freePass = freePassRepository.findById( request.getFreePassId() ).orElseThrow(() ->
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pase libre no encontrado"
            ));

        boolean exists =
                freePass.getRenewals()
                        .stream()
                        .anyMatch(r -> r.getYear().equals(request.getYear()));

        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe una renovación para el año "
                            + request.getYear()
            );
        }

        FreePassRenewalEntity renewal =
                FreePassRenewalEntity.builder()
                        .year(request.getYear())
                        .renewalDate(LocalDate.now())
                        .freePass(freePass)
                        .build();

        renewal = renewalRepository.save(renewal);

        return renewalMapper.toResponse( renewal );
    }

    @Override
    public List<FreePassRenewalResponse> readAll() {

        return renewalRepository.findAll()
                .stream()
                .map(renewalMapper::toResponse)
                .toList();
    }

    @Override
    public List<FreePassRenewalResponse> readByFreePassId(Long freePassId) {
        freePassRepository.findById(freePassId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pase libre no encontrado"
            ));

        return renewalRepository
                .findByFreePassId(freePassId)
                .stream()
                .map(renewalMapper::toResponse)
                .toList();
    }

    @Override
    public FreePassRenewalResponse getById(Long id) {
        var renewal = renewalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Renovación no encontrada"
                ));
        return renewalMapper.toResponse(renewal);
    }

    @Override
    public FreePassRenewalResponse update(FreePassRenewalRequest request, Long id) {
        var renewal = renewalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Renovación no encontrada"
                ));

        renewal.setYear(request.getYear());
        renewal.setRenewalDate(LocalDate.now());

        var updated = renewalRepository.save(renewal);
        return renewalMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        var renewal = renewalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Renovación no encontrada"
                ));
        renewalRepository.delete(renewal);
    }
}