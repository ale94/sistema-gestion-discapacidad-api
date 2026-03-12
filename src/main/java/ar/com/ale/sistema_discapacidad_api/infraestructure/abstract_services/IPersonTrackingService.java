package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonTrackingRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonTrackingResponse;

public interface IPersonTrackingService extends CrudService<PersonTrackingRequest, PersonTrackingResponse, Long> {
}
