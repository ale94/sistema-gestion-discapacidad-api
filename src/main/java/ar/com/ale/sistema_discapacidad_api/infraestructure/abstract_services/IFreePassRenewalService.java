package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.FreePassRenewalRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.FreePassRenewalResponse;

import java.util.List;

public interface IFreePassRenewalService {

    FreePassRenewalResponse create(
            FreePassRenewalRequest request
    );

    List<FreePassRenewalResponse> readAll();
}