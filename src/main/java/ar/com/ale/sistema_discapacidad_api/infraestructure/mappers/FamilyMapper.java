package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FamilyMemberRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FamilyMemberResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.FamilyMemberEntity;

@Mapper(componentModel = "spring")
public interface FamilyMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    FamilyMemberEntity toEntity(FamilyMemberRequest request);

    FamilyMemberResponse toResponse(FamilyMemberEntity entity);


}
