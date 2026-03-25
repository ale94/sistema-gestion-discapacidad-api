package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.AddressRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.AddressResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    AddressEntity toEntity(AddressRequest request);

    AddressResponse toResponse(AddressEntity entity);

}
