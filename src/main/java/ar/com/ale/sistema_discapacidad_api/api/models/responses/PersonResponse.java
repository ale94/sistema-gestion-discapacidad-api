package ar.com.ale.sistema_discapacidad_api.api.models.responses;

import java.io.Serializable;
import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;

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
    private String dni;
    private String civilStatus;
    private LocalDate dateBirth;
    private String tutor;
    private String phone;
    private String gender;
    private LocalDate registrationDate;
    private Status status;

    private EducationResponse education;
    private AddressResponse address;
    private WorkResponse work;
    private HealthResponse health;
    private BenefitResponse benefit;

}
