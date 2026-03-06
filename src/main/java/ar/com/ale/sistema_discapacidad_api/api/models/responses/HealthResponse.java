package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthResponse implements Serializable {
    private Long id;
    private String cudNumber;
    private Boolean activeCud;
    private Boolean rehabilitationTreatment;
    private String diagnostic;
    private String disabilityType;
}
