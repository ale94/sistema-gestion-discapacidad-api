package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import java.time.LocalDate;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import lombok.Data;

@Data
public class FreePassRequest {

    private Long personId;

    private String type;

    private LocalDate startDate;

    private String reason;

    private FreePassStatus status;

    private Boolean active;

    private LocalDate freePassExpiration;
}