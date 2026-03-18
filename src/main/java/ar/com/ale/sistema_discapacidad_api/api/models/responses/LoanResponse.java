package ar.com.ale.sistema_discapacidad_api.api.models.responses;

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
public class LoanResponse implements Serializable {
    private Long id;
    private String type;
    private String equipmentNumber;
    private String dni;
    private String applicant;
    private String address;
    private String phone;
    private String year;
    private LocalDate requestDate;
    private LocalDate expiration;
    private LocalDate returnDate;

}
