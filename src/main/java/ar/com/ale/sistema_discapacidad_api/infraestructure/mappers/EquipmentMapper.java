package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.EquipmentEntity;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "equipmentType", ignore = true)
    EquipmentEntity toEntity(EquipmentRequest request);

    EquipmentResponse toResponse(EquipmentEntity entity);

}
