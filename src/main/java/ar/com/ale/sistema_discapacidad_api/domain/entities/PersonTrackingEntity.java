package ar.com.ale.sistema_discapacidad_api.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "person_tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonTrackingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;

    @Column(unique = true, nullable = false)
    private Long dni;
    
    private String indicatorType;
    private Long phone;

    @OneToOne(mappedBy = "personTracking", cascade = CascadeType.ALL)
    private AddressEntity address;
}
