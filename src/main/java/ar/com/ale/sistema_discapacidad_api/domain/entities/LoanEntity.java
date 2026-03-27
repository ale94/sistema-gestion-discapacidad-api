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
import java.time.LocalDate;

@Entity(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String equipmentNumber;
    private Long dni;
    private String applicant;
    private String address;
    private Long phone;
    private String year;
    private LocalDate requestDate;
    private LocalDate expiration;
    private LocalDate returnDate;
}



