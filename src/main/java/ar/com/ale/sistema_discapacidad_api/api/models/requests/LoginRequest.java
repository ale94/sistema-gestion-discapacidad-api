package ar.com.ale.sistema_discapacidad_api.api.models.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}