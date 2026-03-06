package ar.com.ale.sistema_discapacidad_api.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ale.sistema_discapacidad_api.api.models.requests.PersonRegisterRequest;
import ar.com.ale.sistema_discapacidad_api.api.models.responses.PersonResponse;
import ar.com.ale.sistema_discapacidad_api.infraestructure.abstract_services.IPersonService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final IPersonService personService;

    @PostMapping
    ResponseEntity<PersonResponse> save(@RequestBody PersonRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.create(request));
    }

}
