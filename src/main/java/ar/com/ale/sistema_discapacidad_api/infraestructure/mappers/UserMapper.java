package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.UserRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.UserResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserRequest request);

    UserResponse toResponse(UserEntity entity);
}
