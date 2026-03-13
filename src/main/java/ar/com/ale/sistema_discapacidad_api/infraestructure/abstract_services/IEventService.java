package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EventRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EventResponse;

public interface IEventService extends CrudService<EventRequest, EventResponse, Long> {

}
