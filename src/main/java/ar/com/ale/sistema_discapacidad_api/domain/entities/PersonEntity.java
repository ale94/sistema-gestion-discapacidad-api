package ar.com.ale.sistema_discapacidad_api.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import ar.com.ale.sistema_discapacidad_api.util.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String indicatorType;
    private LocalDate consultationDate;

    @OneToOne(mappedBy = "person")
    private EducationEntity education;

    @OneToOne(mappedBy = "person")
    private WorkEntity work;

    @OneToOne(mappedBy = "person")
    private HealthEntity health;

    @OneToOne(mappedBy = "person")
    private AddressEntity address;

    @OneToOne(mappedBy = "person")
    private BenefitEntity benefit;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER, 
        orphanRemoval = true, 
        mappedBy = "person"
    )
    private List<FamilyMemberEntity> familyMembers;

}
