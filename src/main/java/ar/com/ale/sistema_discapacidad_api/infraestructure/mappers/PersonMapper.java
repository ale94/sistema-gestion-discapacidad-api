package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.PersonEntity;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @BeanMapping(ignoreByDefault = true)
    PersonEntity toEntity(PersonRegisterRequest request);

    PersonResponse toResponse(PersonEntity entity);

}
