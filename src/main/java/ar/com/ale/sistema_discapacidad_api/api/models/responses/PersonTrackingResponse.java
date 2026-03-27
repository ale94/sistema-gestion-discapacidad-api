package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonTrackingResponse {

    private Long id;
    private String lastName;
    private String firstName;
    private Long dni;
    private String indicatorType;
    private String address;
    private Long phone;
}
