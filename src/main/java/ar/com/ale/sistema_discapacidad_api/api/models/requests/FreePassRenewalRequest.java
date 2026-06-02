package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FreePassRenewalRequest {

    private Long freePassId;

    private Integer year;

    private LocalDate renewalDate;

}