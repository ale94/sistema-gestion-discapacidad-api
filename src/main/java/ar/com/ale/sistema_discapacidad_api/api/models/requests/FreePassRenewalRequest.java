package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FreePassRenewalRequest {

    private Long freePassId;

    private Integer year;

    private LocalDate renewalDate;
}