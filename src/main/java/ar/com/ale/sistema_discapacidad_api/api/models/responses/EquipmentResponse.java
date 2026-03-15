package ar.com.ale.sistema_discapacidad_api.api.models.responses;

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
public class EquipmentResponse implements Serializable {

    private Long id;
    private String code;
    private Integer totalStock;
    private String status;
    private LocalDate createdAt;

    private EquipmentTypeResponse equipmentType;

}
