package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FreePassResponse {

    private Long id;

    private Long personId;

    private String fullName;

    private FreePassType type;

    private String reason;

    private LocalDate startDate;

    private Boolean active;

    private List<FreePassRenewalResponse> renewals;

    private FreePassStatus status;
}