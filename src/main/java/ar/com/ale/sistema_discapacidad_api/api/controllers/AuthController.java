package ar.com.ale.sistema_discapacidad_api.api.controllers;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.LoginRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.LoginResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return new LoginResponse(
                authService.login(request)
        );
    }
}