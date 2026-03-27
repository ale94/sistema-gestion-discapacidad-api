package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest implements Serializable {
    private String type;
    private String equipmentNumber;
    private Long dni;
    private String applicant;
    private String address;
    private Long phone;
    private LocalDate expiration;
    private LocalDate returnDate;
}
