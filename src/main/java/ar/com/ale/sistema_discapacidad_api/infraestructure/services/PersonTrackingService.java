package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonTrackingRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonTrackingResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.AddressEntity;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonTrackingEntity;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.PersonTrackingRepository;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonTrackingService;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.AddressMapper;
import ar.com.ale.sistema_discapacidad_api.infraestructure.mappers.PersonTrackingMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonTrackingService implements IPersonTrackingService {

    private final PersonTrackingRepository personTrackingRepository;
    private final PersonTrackingMapper personTrackingMapper;
    private final AddressMapper addressMapper;

    @Override
    public PersonTrackingResponse create(PersonTrackingRequest request) {

        if(personTrackingRepository.existsByDni(request.getDni())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe una persona en seguimiento con ese DNI"
            );
        }

        var address = this.addressMapper.toEntity(request.getAddress());
        var personToPersist = PersonTrackingEntity.builder()
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .dni(request.getDni())
                .indicatorType(request.getIndicatorType())
                .address(address)
                .phone(request.getPhone())
                .build();

        address.setPersonTracking(personToPersist);
        personToPersist.setAddress(address);

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
    public PersonTrackingResponse getById(Long id){
        PersonTrackingEntity personTracking = personTrackingRepository.findById(id)
                .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Persona en seguimiento no encontrada"
                        ));
        return this.personTrackingMapper.toResponse(personTracking);
    }

    @Override
    public PersonTrackingResponse update(PersonTrackingRequest request, Long id) {

        if(personTrackingRepository.existsByDniAndIdNot(request.getDni(),id)){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El DNI ya pertenece a otra persona"
            );
        }

        var personToUpdate = this.personTrackingRepository.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona en seguimiento no encontrada")
        );
        personToUpdate.setFirstName(request.getFirstName());
        personToUpdate.setLastName(request.getLastName());
        personToUpdate.setDni(request.getDni());
        personToUpdate.setIndicatorType(request.getIndicatorType());
        personToUpdate.setPhone(request.getPhone());
        if (request.getAddress() != null) {
            AddressEntity existingAddress = personToUpdate.getAddress();
            
            if (existingAddress == null) {
                existingAddress = new AddressEntity();
                existingAddress.setPersonTracking(personToUpdate);
            }
            
            //Modificamos los campos de la dirección existente con los nuevos datos del Request
            existingAddress.setStreet(request.getAddress().getStreet());
            existingAddress.setDistrict(request.getAddress().getDistrict());
            existingAddress.setLocality(request.getAddress().getLocality());
            existingAddress.setProvince(request.getAddress().getProvince());
            
            //Seteamos la dirección modificada (manteniendo el mismo ID original en base de datos)
            personToUpdate.setAddress(existingAddress);
        }
        var personUpdated = this.personTrackingRepository.save(personToUpdate);
        return this.personTrackingMapper.toResponse(personUpdated);
    }

    @Override
    public void delete(Long id) {
        var personToDelete = this.personTrackingRepository.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona en seguimiento no encontrada")
        );
        this.personTrackingRepository.delete(personToDelete);
    }
}
