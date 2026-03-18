package ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoanRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.LoanResponse;

public interface ILoanService extends CrudService<LoanRequest, LoanResponse, Long> {
}
