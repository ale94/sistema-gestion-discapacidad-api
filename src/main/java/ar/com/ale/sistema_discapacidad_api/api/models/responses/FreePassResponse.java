package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "id",
    "personId",
    "fullName",
    "type",
    "reason",
    "startDate",
    "active",
    "renewals",
    "status",
    "createdAt",
    "updatedAt"
})
@Data
@Builder
public class FreePassResponse {

    private Long id;

    private Long personId;

    private String fullName;

    private FreePassType type;

    private String reason;

    private Boolean active;

    private List<FreePassRenewalResponse> renewals;

    private FreePassStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}