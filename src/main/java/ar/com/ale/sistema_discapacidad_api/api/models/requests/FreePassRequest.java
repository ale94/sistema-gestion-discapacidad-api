package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassType;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import lombok.Data;

@Data
public class FreePassRequest {

    private Long personId;

    private FreePassType type;

    private String reason;

    private FreePassStatus status;
}