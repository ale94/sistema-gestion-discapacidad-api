package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonTrackingRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonTrackingResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonTrackingEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonTrackingRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonTrackingService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.PersonTrackingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonTrackingService implements IPersonTrackingService {

    private final PersonTrackingRepository personTrackingRepository;
    private final PersonTrackingMapper personTrackingMapper;

    @Override
    public PersonTrackingResponse create(PersonTrackingRequest request) {
        var personToPersist = PersonTrackingEntity.builder()
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .dni(request.getDni())
                .indicatorType(request.getIndicatorType())
                .address(request.getAddress())
                .phone(request.getPhone())
                .build();
        var personPersisted = this.personTrackingRepository.save(personToPersist);
        return this.personTrackingMapper.toResponse(personPersisted);
    }

    @Override
    public List<PersonTrackingResponse> readAll() {
        return this.personTrackingRepository.findAll()
                .stream()
                .map(personTrackingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PersonTrackingResponse update(PersonTrackingRequest request, Long id) {
        var personToUpdate = this.personTrackingRepository.findById(id).orElseThrow();
        personToUpdate.setFirstName(request.getFirstName());
        personToUpdate.setLastName(request.getLastName());
        personToUpdate.setDni(request.getDni());
        personToUpdate.setIndicatorType(request.getIndicatorType());
        personToUpdate.setAddress(request.getAddress());
        personToUpdate.setPhone(request.getPhone());
        var personUpdated = this.personTrackingRepository.save(personToUpdate);
        return this.personTrackingMapper.toResponse(personUpdated);
    }

    @Override
    public void delete(Long id) {
        var personToDelete = this.personTrackingRepository.findById(id).orElseThrow();
        this.personTrackingRepository.delete(personToDelete);
    }
}
