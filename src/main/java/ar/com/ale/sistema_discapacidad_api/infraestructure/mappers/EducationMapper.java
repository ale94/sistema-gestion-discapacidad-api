package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EducationRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EducationResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EducationEntity;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    EducationEntity toEntity(EducationRequest request);

    EducationResponse toResponse(EducationEntity entity);

}
