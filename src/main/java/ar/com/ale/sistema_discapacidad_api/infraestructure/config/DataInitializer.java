package ar.com.ale.sistema_discapacidad_api.infraestructure.config;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.*;
import ar.com.ale.sistema_discapacidad_api.domain.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ar.com.ale.sistema_discapacidad_api.infraestructure.services.UserService userService;


    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            seedUsers();
        }
    }

    private void seedUsers() {
        var admin = UserRequest.builder()
                .firstName("Maria Eugenia").lastName("Velasquez")
                .dni("34091232").password("admin").role("ADMIN").build();
        userService.create(admin);

        var user1 = UserRequest.builder()
                .firstName("Celeste Licia").lastName("Gutiérrez")
                .dni("33183555").password("1234").role("USER").build();
        userService.create(user1);

        var user2 = UserRequest.builder()
                .firstName("Tamara Zoe").lastName("Zambrano")
                .dni("41902113").password("1234").role("USER").build();
        userService.create(user2);

        var user3 = UserRequest.builder()
                .firstName("Fernanda Antonella").lastName("Cerpa")
                .dni("39201327").password("1234").role("USER").build();
        userService.create(user3);
    }
}
