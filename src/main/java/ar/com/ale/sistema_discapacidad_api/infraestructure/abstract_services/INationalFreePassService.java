package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.NationalFreePassRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.requests.NationalFreePassStatusRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.NationalFreePassResponse;

import java.util.List;

public interface INationalFreePassService extends 
        CrudService<NationalFreePassRequest, NationalFreePassResponse, Long> {

    NationalFreePassResponse readById(Long id);

    List<NationalFreePassResponse> readByPersonId(Long personId);

    NationalFreePassResponse updateStatus(
            Long id,
            NationalFreePassStatusRequest request
    );
}