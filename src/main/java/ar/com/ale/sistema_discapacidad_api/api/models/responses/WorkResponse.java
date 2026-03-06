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
public class WorkResponse implements Serializable {
    private Long id;
    private String companyName;
    private String status;
    private String address;
    private Boolean socialWork;
    private String nameSocialWork;
}
