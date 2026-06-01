package ar.com.ale.sistema_discapacidad_api.infraestructure.services;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoginRequest;
import ar.com.ale.sistema_discapacidad_api.infraestructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
/* 
    public String login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(
                request.getUsername()
        );
    }*/
   public String login(LoginRequest request) {

    try {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

    } catch (Exception e) {

        e.printStackTrace();

        throw e;
    }

    return jwtService.generateToken(
            request.getUsername()
    );
}
}