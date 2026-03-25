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
public class EducationRequest implements Serializable {
    private String name;
    private String address;
    private String educationLevel;
}
