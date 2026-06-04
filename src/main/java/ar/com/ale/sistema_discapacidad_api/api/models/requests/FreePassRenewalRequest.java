package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.Data;

@Data
public class FreePassRenewalRequest {

    private Long freePassId;

    private Integer year;

}