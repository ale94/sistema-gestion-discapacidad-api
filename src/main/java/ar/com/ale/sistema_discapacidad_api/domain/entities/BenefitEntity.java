package ar.com.ale.sistema_discapacidad_api.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "benefit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean federalProgram;
    private Boolean pension;
    private Boolean auh;
    private Boolean merchandise;
    private Boolean freePass;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}
