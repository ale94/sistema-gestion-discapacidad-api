package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "id",
    "personId",
    "fullName",
    "tripDate",
    "ticketQuantity",
    "origin",
    "destination",
    "status",
    "reason",
    "createdAt",
    "updatedAt"
})
@Data
@Builder
public class NationalFreePassResponse {

    private Long id;

    private Long personId;

    private String fullName;

    private LocalDate tripDate;

    private Integer ticketQuantity;

    private String origin;

    private String destination;

    private String reason;

    private FreePassStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}