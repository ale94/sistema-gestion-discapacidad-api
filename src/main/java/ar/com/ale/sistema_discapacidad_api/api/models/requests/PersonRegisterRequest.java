package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRegisterRequest implements Serializable {

    private String firstName;
    private String lastName;
    private String dni;
    private String civilStatus;
    private LocalDate dateBirth;
    private String tutor;
    private String phone;
    private String gender;

    private AddressRequest address;
    private EducationRequest education;
    private WorkRequest work;
    private HealthRequest health;
    private BenefitRequest benefit;

    private List<FamilyMemberRequest> familyMembers;

}
