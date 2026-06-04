package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FreePassRenewalMapper.class)
public interface FreePassMapper {

    @Mapping( target = "personId", source = "person.id" )
    @Mapping( target = "fullName",
            expression =
                "java(entity.getPerson().getLastName() + \", \" + entity.getPerson().getFirstName())"
    )
    
    FreePassResponse toResponse(
            FreePassEntity entity
    );
}