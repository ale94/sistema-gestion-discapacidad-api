package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentTypeRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentTypeResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EquipmentTypeEntity;

@Mapper(componentModel = "spring")
public interface EquipmentTypeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    EquipmentTypeEntity toEntity(EquipmentTypeRequest request);

    EquipmentTypeResponse toResponse(EquipmentTypeEntity entity);

}
