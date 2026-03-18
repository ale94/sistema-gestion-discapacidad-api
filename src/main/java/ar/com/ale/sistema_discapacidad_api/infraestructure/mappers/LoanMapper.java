package ar.com.ale.sistema_discapacidad_api.infraestructure.mappers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoanRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.LoanResponse;
import ar.com.ale.sistema_discapacidad_api.domain.entities.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "year", ignore = true)
    @Mapping(target = "requestDate", ignore = true)
    LoanEntity toEntity(LoanRequest request);

    LoanResponse toResponse(LoanEntity entity);
}
