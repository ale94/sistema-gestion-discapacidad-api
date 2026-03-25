package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitRequest implements Serializable {

    private Boolean federalProgram;
    private Boolean pension;
    private Boolean auh;
    private Boolean merchandise;
    private Boolean freePass;

}
