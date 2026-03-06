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
public class AddressResponse implements Serializable {
    private Long id;
    private String street;
    private String district;
    private String locality;
    private String province;
}
