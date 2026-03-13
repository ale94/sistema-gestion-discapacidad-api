package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EventRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EventResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EventEntity;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    EventEntity toEntity(EventRequest request);

    EventResponse toResponse(EventEntity entity);

}
