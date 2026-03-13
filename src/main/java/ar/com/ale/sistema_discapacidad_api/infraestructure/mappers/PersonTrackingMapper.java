package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonTrackingRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonTrackingResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonTrackingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonTrackingMapper {

    @Mapping(target = "id", ignore = true)
    PersonTrackingEntity toEntity(PersonTrackingRequest request);

    PersonTrackingResponse toResponse(PersonTrackingEntity entity);
}
