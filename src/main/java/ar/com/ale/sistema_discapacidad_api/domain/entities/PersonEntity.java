package ar.com.ale.sistema_discapacidad_api.domain.entities;

import ar.com.ale.sistema_discapacidad_api.util.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Status status;
    private String indicatorType;
    private LocalDate consultationDate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private EducationEntity education;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private WorkEntity work;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private HealthEntity health;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private BenefitEntity benefit;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "person")
    private List<FamilyMemberEntity> familyMembers = new ArrayList<>();

    public void addFamilyMembers(List<FamilyMemberEntity> members) {
        if (members == null)
            return;
        members.forEach(this::addFamilyMember);
    }

    public void addFamilyMember(FamilyMemberEntity member) {
        this.familyMembers.add(member);
        member.setPerson(this);
    }

    public void deleteFamilyMember(FamilyMemberEntity member) {
        this.familyMembers.remove(member);
    }

}
