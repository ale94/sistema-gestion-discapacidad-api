package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.Data;

import java.time.LocalDate;

import ar.com.ale.sistema_discapacidad_api.domain.enums.FreePassStatus;

@Data
public class NationalFreePassRequest {

    private Long personId;

    private LocalDate requestDate;

    private LocalDate tripDate;

    private Integer ticketQuantity;

    private String origin;

    private String destination;

    private FreePassStatus status;

    private String reason;
}