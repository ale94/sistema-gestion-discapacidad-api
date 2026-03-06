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
public class PersonRegisterRequest implements Serializable {

    private String firstName;
    private String lastName;
    private String dni;
    private String civilStatus;
    private LocalDate dateBirth;
    private String tutor;
    private String phone;
    private String gender;

    // Address fields
    private String street;
    private String district;
    private String locality;
    private String province;

    // Education fields
    private String schoolName;
    private String educationAddress;
    private String educationLevel;

    // Work fields
    private String companyName;
    private String employmentStatus;
    private String workAddress;
    private Boolean socialWork;
    private String nameSocialWork;

    // Health fields
    private String cudNumber;
    private Boolean activeCud;
    private Boolean rehabilitationTreatment;
    private String diagnostic;
    private String disabilityType;

    // Benefit fields
    private Boolean federalProgram;
    private Boolean pension;
    private Boolean auh;
    private Boolean merchandise;
    private Boolean freePass;

}
