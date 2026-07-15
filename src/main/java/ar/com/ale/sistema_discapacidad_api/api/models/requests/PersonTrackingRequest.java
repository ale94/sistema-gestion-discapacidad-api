package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonTrackingRequest implements Serializable {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotNull(message = "El DNI es obligatorio")
    @Min(value = 1000000)
    @Max(value = 99999999)
    private Long dni;

    
    private AddressRequest address;

    @NotBlank(message = "El tipo de indicador es obligatorio")
    private String indicatorType;

    @NotNull(message = "El teléfono es obligatorio")
    private Long phone;

}


