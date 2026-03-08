package ar.com.ale.sistema_discapacidad_api.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
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

@Entity(name = "work")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String status;
    private String address;
    private Boolean socialWork;
    @Column(name = "name_social_work")
    private String nameSocialWork;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}
