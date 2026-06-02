package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import lombok.Data;

@Data
public class FreePassStatusRequest {

    private FreePassStatus status;

}