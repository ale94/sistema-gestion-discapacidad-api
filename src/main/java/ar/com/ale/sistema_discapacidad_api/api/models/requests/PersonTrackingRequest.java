package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonTrackingRequest implements Serializable {

    private String firstName;
    private String lastName;
    private String dni;
    private String address;
    private String indicatorType;
    private String phone;

}


