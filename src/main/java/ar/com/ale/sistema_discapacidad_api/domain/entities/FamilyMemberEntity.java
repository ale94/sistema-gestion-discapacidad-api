package ar.com.ale.sistema_discapacidad_api.domain.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "family_member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyMemberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fistName;
    private String lastName;
    private String dni;
    private Integer age;
    private String civilStatus;
    private String parentage;
    private String occupation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}
