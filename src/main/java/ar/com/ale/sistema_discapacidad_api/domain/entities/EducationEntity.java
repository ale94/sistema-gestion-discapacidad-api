package ar.com.ale.sistema_discapacidad_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "education")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String educationLevel;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}
