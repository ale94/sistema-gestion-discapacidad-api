package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "id",
    "freePassId",
    "year",
    "createdAt"
})
@Data
@Builder
public class FreePassRenewalResponse {

    private Long id;

    private Long freePassId;

    private Integer year;

    private LocalDateTime createdAt;

}