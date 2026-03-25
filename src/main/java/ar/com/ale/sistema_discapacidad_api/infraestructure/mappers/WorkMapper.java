package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.WorkRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.WorkResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.WorkEntity;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    WorkEntity toEntity(WorkRequest request);

    WorkResponse toResponse(WorkEntity entity);

}
