package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "familyMembers", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "education", ignore = true)
    @Mapping(target = "work", ignore = true)
    @Mapping(target = "health", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "benefit", ignore = true)
    PersonEntity toEntity(PersonRegisterRequest request);

    @Mapping(target = "id", ignore = true)
    PersonResponse toResponse(PersonEntity entity);

}
