package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import java.util.List;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;

public interface IPersonService {

    PersonResponse create(PersonRegisterRequest request);

    List<PersonResponse> readAll();

    PersonResponse update(PersonRegisterRequest registerRequest, Long id);

    void delete(Long id);

}
