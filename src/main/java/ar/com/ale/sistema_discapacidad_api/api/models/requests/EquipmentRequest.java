package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentRequest implements Serializable {

    private Integer totalStock;
    private String idEquipmentType;
    private String status;

}
