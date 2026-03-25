package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.HealthRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.HealthResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.HealthEntity;

@Mapper(componentModel = "spring")
public interface HealthMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    HealthEntity toEntity(HealthRequest request);

    HealthResponse toResponse(HealthEntity entity);

}
