package ar.com.ale.sistema_discapacidad_api.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long dni;
    private String indicatorType;
    private String address;
    private Long phone;
}
