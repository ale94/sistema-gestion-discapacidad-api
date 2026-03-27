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
public class FamilyMemberResponse implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Long dni;
    private Integer age;
    private String civilStatus;
    private String parentage;
    private String occupation;
}
