package ar.com.ale.sistema_discapacidad_api.api.models.responses;

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
public class PersonResponse implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Long dni;
    private String civilStatus;
    private LocalDate dateBirth;
    private String tutor;
    private Long phone;
    private String gender;
    private LocalDate registrationDate;

    private EducationResponse education;
    private AddressResponse address;
    private WorkResponse work;
    private HealthResponse health;
    private BenefitResponse benefit;

    private List<FamilyMemberResponse> familyMembers;

}
