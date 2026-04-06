package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.UserRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long> {
}
