package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FreePassRenewalResponse {

    private Long id;

    private Long freePassId;

    private Integer year;

    private LocalDate renewalDate;

}