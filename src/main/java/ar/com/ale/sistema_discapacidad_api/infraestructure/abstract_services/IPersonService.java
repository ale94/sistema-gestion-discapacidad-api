package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;

public interface IPersonService extends CrudService<PersonRegisterRequest, PersonResponse, Long> {
}
