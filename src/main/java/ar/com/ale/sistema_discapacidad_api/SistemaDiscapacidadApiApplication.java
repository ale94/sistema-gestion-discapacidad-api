package ar.com.ale.sistema_discapacidad_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SistemaDiscapacidadApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaDiscapacidadApiApplication.class, args);
    }

}
