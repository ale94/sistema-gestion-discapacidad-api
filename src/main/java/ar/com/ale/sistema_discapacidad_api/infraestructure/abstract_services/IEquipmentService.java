package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentResponse;

public interface IEquipmentService extends CrudService<EquipmentRequest, EquipmentResponse, Long> {

}
