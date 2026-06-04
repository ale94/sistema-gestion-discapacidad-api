package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRenewalRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassRenewalResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassRenewalEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FreePassRenewalRepository;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.FreePassRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.FreePassRenewalMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IFreePassRenewalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        var freePass = freePassRepository.findById( request.getFreePassId() ).orElseThrow();

        boolean exists =
                freePass.getRenewals()
                        .stream()
                        .anyMatch(r -> r.getYear().equals(request.getYear()));

        if (exists) {
            throw new RuntimeException(
                    "Ya existe una renovación para ese año"
            );
        }

        FreePassRenewalEntity renewal =
                FreePassRenewalEntity.builder()
                        .year(request.getYear())
                        .renewalDate( request.getRenewalDate() )
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
}