package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import ar.com.ale.sistema_discapacidad_api.api.models.responses.NationalFreePassResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.NationalFreePassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NationalFreePassMapper {

    @Mapping(
            target = "personId",
            source = "person.id"
    )
    @Mapping(
            target = "dni",
            source = "person.dni"
    )
    @Mapping(
            target = "fullName",
            expression =
            "java(entity.getPerson().getLastName() + \", \" + entity.getPerson().getFirstName())"
    )
    NationalFreePassResponse toResponse(
            NationalFreePassEntity entity
    );
}