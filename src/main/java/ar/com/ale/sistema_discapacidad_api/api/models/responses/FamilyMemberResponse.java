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
public class FamilyMemberResponse implements Serializable {

    private Long id;
    private String fullName;
    private Long dni;
    private LocalDate dateBirth;
    private Long phone;
    private String parentage;
}
