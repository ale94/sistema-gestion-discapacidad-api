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
public class UserResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private Long dni;
    private String role;
    private Boolean active;
}
