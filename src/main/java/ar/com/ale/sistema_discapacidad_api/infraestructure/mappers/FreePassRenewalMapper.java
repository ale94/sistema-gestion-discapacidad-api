package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassRenewalResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FreePassRenewalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FreePassRenewalMapper {

    @Mapping(
            target = "freePassId",
            source = "freePass.id"
    )
    FreePassRenewalResponse toResponse(
            FreePassRenewalEntity entity
    );
}