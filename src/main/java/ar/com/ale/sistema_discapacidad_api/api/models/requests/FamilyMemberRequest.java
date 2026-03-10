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
public class FamilyMemberRequest implements Serializable {

    private String firstName;
    private String lastName;
    private String dni;
    private Integer age;
    private String civilStatus;
    private String parentage;
    private String occupation;
}
