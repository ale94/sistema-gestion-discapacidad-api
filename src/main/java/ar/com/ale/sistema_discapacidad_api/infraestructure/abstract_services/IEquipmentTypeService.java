package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.EquipmentTypeRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.EquipmentTypeResponse;

public interface IEquipmentTypeService extends CrudService<EquipmentTypeRequest, EquipmentTypeResponse, Long> {

}
