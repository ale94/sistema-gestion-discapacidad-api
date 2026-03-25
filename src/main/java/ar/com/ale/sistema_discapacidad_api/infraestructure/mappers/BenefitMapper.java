package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.BenefitRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.BenefitResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.BenefitEntity;

@Mapper(componentModel = "spring")
public interface BenefitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    BenefitEntity toEntity(BenefitRequest request);

    BenefitResponse toResponse(BenefitEntity entity);

}
