package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassResponse;

public interface IFreePassService extends CrudService<FreePassRequest, FreePassResponse, Long> {

    FreePassResponse getById(Long id);

    FreePassResponse updateStatus(Long id, FreePassStatusRequest request);
}